package com.example.bankapplication.repository;

import com.example.bankapplication.model.entity.UserLogin;
import com.example.bankapplication.model.enums.UserLoginStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {

    Optional<UserLogin> findUserLoginByIdAndStatus(Long id, UserLoginStatus status);

}
