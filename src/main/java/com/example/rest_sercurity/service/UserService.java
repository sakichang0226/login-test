package com.example.rest_sercurity.service;

import com.example.rest_sercurity.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final String EXISTING_EMAIL = "test@test.com";

    public Optional<UserEntity> findByEmail(String email) {
        if (!email.equals(EXISTING_EMAIL)) {
            return Optional.empty();
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail(email);
        userEntity.setPassword("$2a$12$c6XVYlmeXvZ8WR8DjhfYc.hSTS0THD69WmNGm1ez.JDisUF42XTOe");
        userEntity.setRole("ROLE_ADMIN");
        userEntity.setExtraInfo("My nice admin");
        return Optional.of(userEntity);
    }

}
