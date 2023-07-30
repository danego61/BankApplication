package com.example.bankapplication.controller;

import com.example.bankapplication.model.request.CreateUserRequest;
import com.example.bankapplication.model.response.CreateUserResponse;
import com.example.bankapplication.service.UserService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
@RateLimiter(name = "default")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody final CreateUserRequest createUserRequest) {
        return userService.createUser(createUserRequest);
    }

}
