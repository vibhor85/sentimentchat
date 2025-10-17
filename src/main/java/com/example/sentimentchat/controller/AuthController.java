package com.example.sentimentchat.controller;

import com.example.sentimentchat.dtos.auth.LoginRequest;
import com.example.sentimentchat.dtos.auth.LoginResponse;
import com.example.sentimentchat.dtos.auth.SignupRequest;
import com.example.sentimentchat.dtos.auth.SignupResponse;
import com.example.sentimentchat.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse handleLogin(@RequestBody LoginRequest authRequest) {
        log.info("Login attempt for user: {}", authRequest.username());
        return authService.login(authRequest);
    }

    @PostMapping("/signup")
    public SignupResponse handleSignup(@RequestBody SignupRequest signupRequest) {
        log.info("Signup attempt for user: {}", signupRequest.username());
        return authService.signup(signupRequest);
    }

}
