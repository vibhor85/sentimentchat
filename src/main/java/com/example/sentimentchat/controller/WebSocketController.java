package com.example.sentimentchat.controller;

import com.example.sentimentchat.dtos.message.MessageRequest;
import com.example.sentimentchat.dtos.message.MessageResponse;
import com.example.sentimentchat.dtos.roomMember.RoomMemberResponse;
import com.example.sentimentchat.service.MessageService;
import com.example.sentimentchat.service.RoomMembersService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    private final MessageService messageService;
    private final RoomMembersService roomMembersService;

    @MessageMapping("/sendMessage/{roomId}")
    public void sendMessage(@DestinationVariable UUID roomId, @Payload MessageRequest messageRequest) {

        System.out.println(messageRequest);

        MessageResponse messageResponse = messageService.saveMessage(messageRequest, roomId);
        simpMessagingTemplate.convertAndSend("/topic/room/" + roomId.toString(), messageResponse);
    }

    @MessageMapping("room/{roomId}/addUser/{userId}")
    public void addUser(@DestinationVariable UUID roomId, @DestinationVariable UUID userId) {

        System.out.println("Adding user " + userId + " to room " + roomId);
        RoomMemberResponse roomMemberResponse = roomMembersService.addMemberToRoom(roomId, userId, false);
        System.out.println(roomMemberResponse);
        simpMessagingTemplate.convertAndSend("/topic/room/" + roomId.toString() + "/new-member", userId);

    }
}
