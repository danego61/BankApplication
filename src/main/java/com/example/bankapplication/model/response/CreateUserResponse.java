package com.example.bankapplication.model.response;

import com.example.bankapplication.model.base.ResponseBase;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
public class CreateUserResponse implements ResponseBase {

    @Serial
    private static final long serialVersionUID = 2411126440524981382L;

    private String name;

    private String surname;

    private String email;

    private String telephone;

    private Boolean success;

}
