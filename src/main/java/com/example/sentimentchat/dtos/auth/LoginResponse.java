package com.example.sentimentchat.dtos.auth;

import java.util.UUID;

public record LoginResponse(String token, String username, UUID id) {
}
