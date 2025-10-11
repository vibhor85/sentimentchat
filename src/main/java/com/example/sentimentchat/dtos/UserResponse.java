package com.example.sentimentchat.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String username,
        String bio,
        String profileImage,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
