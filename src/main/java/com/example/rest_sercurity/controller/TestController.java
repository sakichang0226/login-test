package com.example.rest_sercurity.controller;

import com.example.rest_sercurity.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    @GetMapping("/")
    public String test() {
        return "Hello World";
    }

    @GetMapping("/secured")
    public String admin(@AuthenticationPrincipal UserPrincipal principal) {
        return "If you see this. logged in " + principal.getEmail() + " User Id:" + principal.getUserId();
    }
}
