package com.example.bankapplication.model.request;

import com.example.bankapplication.model.base.RequestBase;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
public class UserLoginRequest implements RequestBase {

    @Serial
    private static final long serialVersionUID = -8181223293076612253L;

    private String email;

    private String password;

}
