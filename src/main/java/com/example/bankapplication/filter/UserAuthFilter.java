package com.example.bankapplication.filter;

import com.example.bankapplication.model.entity.UserLogin;
import com.example.bankapplication.model.enums.UserLoginStatus;
import com.example.bankapplication.repository.UserLoginRepository;
import jakarta.servlet.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public class UserAuthFilter implements Filter {

    private final UserLoginRepository userLoginRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isLoggedOut = false;

        if (auth != null) {
            if (auth.getCredentials() instanceof Jwt s) {
                Long loginId = s.getClaim("login");

                Optional<UserLogin> userLoginOptional =
                        userLoginRepository.findUserLoginByIdAndStatus(loginId, UserLoginStatus.LOGGED);

                if (userLoginOptional.isEmpty()) {
                    isLoggedOut = true;
                }

            } else {
                isLoggedOut = true;
            }

        }

        if (isLoggedOut) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

}
