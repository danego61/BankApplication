package com.example.bankapplication.service.impl;

import com.example.bankapplication.mapper.UserMapper;
import com.example.bankapplication.model.entity.User;
import com.example.bankapplication.model.entity.UserLogin;
import com.example.bankapplication.model.enums.UserLoginStatus;
import com.example.bankapplication.model.request.UserLoginRequest;
import com.example.bankapplication.model.response.UserLoginResponse;
import com.example.bankapplication.repository.UserLoginRepository;
import com.example.bankapplication.service.LoginService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;

    private final JwtEncoder jwtEncoder;

    private final UserMapper userMapper;

    private final UserLoginRepository userLoginRepository;

    @Transactional
    @Override
    public ResponseEntity<UserLoginResponse> userLogin(UserLoginRequest userLoginRequest, String ip) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequest.getEmail(), userLoginRequest.getPassword()));

            User user = (User) authentication.getPrincipal();

            var now = Instant.now();
            var expiry = 36000L;

            var scope =
                    authentication.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(joining(" "));

            UserLogin userLogin = userLoginRepository.save(getUserLogin(user.getUserId(), ip));

            var claims =
                    JwtClaimsSet.builder()
                            .issuedAt(now)
                            .expiresAt(now.plusSeconds(expiry))
                            .subject(format("%s", user.getUserId()))
                            .claim("login", userLogin.getId())
                            .claim("roles", scope)
                            .build();

            var token = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .body(userMapper.getLogin(user, token));
        } catch (BadCredentialsException ex) {
            UserLoginResponse errorResponse = new UserLoginResponse();
            errorResponse.setError("Email or password incorrect!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

    @Override
    public ResponseEntity<Boolean> userLogout() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.getCredentials() instanceof Jwt s) {

            Long loginId = s.getClaim("login");

            Optional<UserLogin> userLoginOptional = userLoginRepository.findById(loginId);

            if (userLoginOptional.isPresent()) {
                userLoginOptional.get().setStatus(UserLoginStatus.LOGOUT);
                userLoginRepository.save(userLoginOptional.get());
                return ResponseEntity.ok(true);
            }

        }

        return ResponseEntity.badRequest().body(false);
    }

    private UserLogin getUserLogin(final Long userId, final String ip) {
        UserLogin userLogin = new UserLogin();

        userLogin.setUserId(userId);
        userLogin.setStatus(UserLoginStatus.LOGGED);
        userLogin.setIp(ip);

        return userLogin;
    }

}
