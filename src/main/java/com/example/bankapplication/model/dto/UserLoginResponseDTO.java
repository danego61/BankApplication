package com.example.bankapplication.model.dto;

import com.example.bankapplication.model.base.DTOBase;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
public class UserLoginResponseDTO implements DTOBase {

    @Serial
    private static final long serialVersionUID = 7936740690525354773L;

    private String name;

    private String surname;

    private String email;

    private String telephone;

}
