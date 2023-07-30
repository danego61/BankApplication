package com.example.bankapplication.service;

import com.example.bankapplication.model.entity.User;
import com.example.bankapplication.model.request.CreateUserRequest;
import com.example.bankapplication.model.response.CreateUserResponse;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {

    ResponseEntity<CreateUserResponse> createUser(CreateUserRequest createUserRequest);

    Optional<User> loadUser(String email);

    Optional<User> loadUser(Long userId);

}
