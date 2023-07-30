package com.example.bankapplication.model.request;

import com.example.bankapplication.model.base.RequestBase;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
public class MoneyTransferRequest implements RequestBase {

    @Serial
    private static final long serialVersionUID = 1293805046693721307L;

    private String email;

}
