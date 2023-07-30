package com.example.bankapplication.controller;

import com.example.bankapplication.model.request.MoneyTransferRequest;
import com.example.bankapplication.model.response.MoneyTransferResponse;
import com.example.bankapplication.service.WalletService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/wallet")
@RequiredArgsConstructor
@RateLimiter(name = "default")
public class WalletController {

    private final WalletService walletService;

    @PostMapping("/transfer")
    public ResponseEntity<MoneyTransferResponse> transfer(final MoneyTransferRequest moneyTransferRequest) {
        return walletService.transfer(moneyTransferRequest);
    }

}
