package com.example.bankapplication.service;

import com.example.bankapplication.model.request.MoneyTransferRequest;
import com.example.bankapplication.model.response.MoneyTransferResponse;
import org.springframework.http.ResponseEntity;

public interface WalletService {

    ResponseEntity<MoneyTransferResponse> transfer(MoneyTransferRequest moneyTransferRequest);

}
