package com.example.bankapplication.model.response;

import com.example.bankapplication.model.base.ResponseBase;
import com.example.bankapplication.model.dto.UserLoginResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
public class UserLoginResponse implements ResponseBase {

    @Serial
    private static final long serialVersionUID = 3496720192282196737L;

    private String error;

    private UserLoginResponseDTO user;

    private String token;

}
