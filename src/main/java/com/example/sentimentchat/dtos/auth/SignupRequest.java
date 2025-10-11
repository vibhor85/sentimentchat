package com.example.sentimentchat.dtos.auth;

public record SignupRequest(
        String username,
        String password,
        String bio,
        String profileImage) {
}
