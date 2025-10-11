package com.example.sentimentchat.dtos.message;

import com.example.sentimentchat.entity.Content;

import java.util.UUID;

public record MessageRequest(
        Content content,
        UUID senderId
){}
