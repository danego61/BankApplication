package com.example.bankapplication.model.request;

import com.example.bankapplication.model.base.RequestBase;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
public class CreateUserRequest implements RequestBase {

    @Serial
    private static final long serialVersionUID = -8393347050596349869L;

    private String name;

    private String surname;

    private String email;

    private String telephone;

    private String password;

    private String rePassword;

}