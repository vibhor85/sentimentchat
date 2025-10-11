package com.example.sentimentchat.dtos.room;

import com.example.sentimentchat.enums.RoomType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record RoomRequest(
        String name,
        String description,
        RoomType roomType,
        boolean isPrivate,
        UUID createdBy
) {

    @JsonCreator
    public RoomRequest(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("roomType") RoomType roomType,
            @JsonProperty("isPrivate") Boolean isPrivate,
            @JsonProperty("createdBy") UUID createdBy
    ) {

        this(name, description, roomType == null ? RoomType.DIRECT : roomType, isPrivate == null || isPrivate, createdBy);
    }
}
