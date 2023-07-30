package com.example.bankapplication.service;

import com.example.bankapplication.model.request.UserLoginRequest;
import com.example.bankapplication.model.response.UserLoginResponse;
import org.springframework.http.ResponseEntity;

public interface LoginService {

    ResponseEntity<UserLoginResponse> userLogin(final UserLoginRequest userLoginRequest, String ip);

    ResponseEntity<Boolean> userLogout();

}
