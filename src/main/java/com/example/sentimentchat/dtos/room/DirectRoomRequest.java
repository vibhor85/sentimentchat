package com.example.sentimentchat.dtos.room;

import java.util.UUID;

public record DirectRoomRequest(UUID userId1, UUID userId2) {
}
