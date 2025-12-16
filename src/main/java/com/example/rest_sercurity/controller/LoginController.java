package com.example.rest_sercurity.controller;

import com.example.rest_sercurity.config.JwtIssuer;
import com.example.rest_sercurity.request.LoginRequest;
import com.example.rest_sercurity.response.LoginResponse;
import com.example.rest_sercurity.security.UserPrincipal;
import com.example.rest_sercurity.security.UserPrincipalAuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final JwtIssuer jwtIssuer;

    private final AuthenticationManager authenticationManager;

    @PostMapping("api/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Validated LoginRequest loginRequest) {

        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        var roles = principal.getAuthorities().stream().map(Object::toString).toList();

        String token = jwtIssuer.issue(principal.getUserId(), principal.getEmail(), roles);
        System.out.println(token);
        return new ResponseEntity<>(LoginResponse.builder().token(token).build(),HttpStatus.OK);
    }
}
