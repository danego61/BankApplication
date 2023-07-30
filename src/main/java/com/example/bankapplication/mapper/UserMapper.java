package com.example.bankapplication.mapper;

import com.example.bankapplication.model.entity.User;
import com.example.bankapplication.model.entity.UserWallet;
import com.example.bankapplication.model.request.CreateUserRequest;
import com.example.bankapplication.model.response.CreateUserResponse;
import com.example.bankapplication.model.response.UserLoginResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    public abstract User getEntity(CreateUserRequest createUserRequest);

    public abstract UserLoginResponse getLogin(User user, String token);

    @Mapping(target = "success", expression = "java(true)")
    public abstract CreateUserResponse getCreateUserResponse(User user);

    public UserWallet getDefaultUserWallet() {
        UserWallet userWallet = new UserWallet();

        userWallet.setMoney(8D);

        return userWallet;
    }

}
