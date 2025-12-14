package com.example.rest_sercurity.controller;

import com.example.rest_sercurity.config.JwtIssuer;
import com.example.rest_sercurity.request.LoginRequest;
import com.example.rest_sercurity.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final JwtIssuer jwtIssuer;

    @PostMapping("api/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Validated LoginRequest loginRequest) {

        String token = jwtIssuer.issue(1L, loginRequest.getPassword(), List.of("user"));

        return new ResponseEntity<>(LoginResponse.builder().token(token).build(),HttpStatus.OK);
    }
}
