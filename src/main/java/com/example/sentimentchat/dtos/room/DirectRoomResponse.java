
package com.example.sentimentchat.dtos.room;

import com.example.sentimentchat.enums.RoomType;

import java.time.LocalDateTime;
import java.util.UUID;

public record DirectRoomResponse(
        UUID id,
        String name,
        String description,
        RoomType roomType,
        boolean isPrivate,
        UUID createdBy,
        Members members,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}