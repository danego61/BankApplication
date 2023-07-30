package com.example.bankapplication.controller;

import com.example.bankapplication.model.request.UserLoginRequest;
import com.example.bankapplication.model.response.UserLoginResponse;
import com.example.bankapplication.service.LoginService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/login")
@RequiredArgsConstructor
@RateLimiter(name = "default")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/user")
    public ResponseEntity<UserLoginResponse> userLogin(@RequestBody final UserLoginRequest userLoginRequest,
                                                       final HttpServletRequest request) {
        return loginService.userLogin(userLoginRequest, request.getRemoteAddr());
    }

    @DeleteMapping("/user")
    public ResponseEntity<Boolean> userLogout() {
        return loginService.userLogout();
    }

}
