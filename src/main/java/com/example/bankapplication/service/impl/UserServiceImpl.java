package com.example.bankapplication.service.impl;

import com.example.bankapplication.mapper.UserMapper;
import com.example.bankapplication.model.entity.User;
import com.example.bankapplication.model.request.CreateUserRequest;
import com.example.bankapplication.model.response.CreateUserResponse;
import com.example.bankapplication.repository.UserRepository;
import com.example.bankapplication.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public ResponseEntity<CreateUserResponse> createUser(final CreateUserRequest createUserRequest) {

        if (userRepository.findByEmail(createUserRequest.getEmail()).isPresent()) {
            throw new ValidationException("Username exists!");
        }

        if (!createUserRequest.getPassword().equals(createUserRequest.getRePassword())) {
            throw new ValidationException("Passwords don't match!");
        }

        User user = userMapper.getEntity(createUserRequest);
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        user.setWallet(userMapper.getDefaultUserWallet());

        userRepository.save(user);

        return ResponseEntity.ok(userMapper.getCreateUserResponse(user));
    }

    @Override
    public Optional<User> loadUser(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> loadUser(Long userId) {
        return userRepository.findById(userId);
    }

}
