package com.example.sentimentchat.dtos.auth;

import java.util.UUID;

public record SignupResponse(
        UUID id,
        String username,
        String bio,
        String profileImage
) {
}
