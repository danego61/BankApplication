package com.example.bankapplication.model.response;

import com.example.bankapplication.model.base.RequestBase;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
public class MoneyTransferResponse implements RequestBase {

    @Serial
    private static final long serialVersionUID = -33524916525485651L;

    private String totalMoney;

    private boolean success;


}
