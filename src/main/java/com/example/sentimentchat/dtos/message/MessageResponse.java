package com.example.sentimentchat.dtos.message;

import com.example.sentimentchat.entity.Content;

import java.util.UUID;

public record MessageResponse(
        String id,
        UUID roomId,
        UUID senderId,
        Content content,
        String editedAt,
        String createdAt
) {
}
