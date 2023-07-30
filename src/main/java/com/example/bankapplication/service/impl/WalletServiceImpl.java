package com.example.bankapplication.service.impl;

import com.example.bankapplication.model.entity.User;
import com.example.bankapplication.model.entity.UserWallet;
import com.example.bankapplication.model.request.MoneyTransferRequest;
import com.example.bankapplication.model.response.MoneyTransferResponse;
import com.example.bankapplication.repository.UserWalletRepository;
import com.example.bankapplication.service.UserService;
import com.example.bankapplication.service.WalletService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private static final double DEFAULT_TRANSFER = 1.1D;

    private final UserWalletRepository walletRepository;

    private final UserService userService;

    @Transactional
    @Override
    public ResponseEntity<MoneyTransferResponse> transfer(final MoneyTransferRequest moneyTransferRequest) {
        MoneyTransferResponse response = new MoneyTransferResponse();
        Long loggedUserId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        Optional<User> loggedUserOptional = userService.loadUser(loggedUserId);
        Optional<User> transferUserOptional = userService.loadUser(moneyTransferRequest.getEmail());

        if (loggedUserOptional.isPresent() && transferUserOptional.isPresent()) {

            User loggedUser = loggedUserOptional.get();
            UserWallet userWallet = loggedUser.getWallet();
            UserWallet transferUserWallet = transferUserOptional.get().getWallet();

            if (userWallet.getMoney() - DEFAULT_TRANSFER > 0 && !Objects.equals(loggedUser.getEmail(), moneyTransferRequest.getEmail())) {

                Double newMoney = userWallet.getMoney() - DEFAULT_TRANSFER;
                userWallet.setMoney(newMoney);
                transferUserWallet.setMoney(transferUserWallet.getMoney() + DEFAULT_TRANSFER);
                walletRepository.saveAll(List.of(userWallet, transferUserWallet));
                response.setTotalMoney(format("%s$", newMoney));
                response.setSuccess(true);

            }

        }

        return ResponseEntity.ok(response);
    }

}
