package com.example.sentimentchat.dtos.room;

import com.example.sentimentchat.enums.RoomType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record RoomResponse(UUID id, String name, String description, RoomType roomType, boolean isPrivate,
                           UUID createdBy,
                           List<Member> members,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt) {
}
