package com.example.bankapplication.repository;

import com.example.bankapplication.model.entity.UserWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWalletRepository extends JpaRepository<UserWallet, Long> {

}
