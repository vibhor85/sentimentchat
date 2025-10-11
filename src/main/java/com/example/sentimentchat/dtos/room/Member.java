package com.example.sentimentchat.dtos.room;

import java.util.UUID;

public record Member(
        UUID id,
        String username
) {
}
