package com.example.sentimentchat.dtos.roomMember;

import com.example.sentimentchat.enums.Role;

import java.time.LocalDateTime;
import java.util.UUID;

public record RoomMemberResponse(
        UUID id,
        UUID roomId,
        UUID userId,
        Role role,
        LocalDateTime getJoinedAt
) {
}
