package com.example.sentimentchat.controller;

import com.example.sentimentchat.dtos.message.MessageResponse;
import com.example.sentimentchat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<MessageResponse>> getAllMessagesByRoomId(@PathVariable UUID roomId) {
        List<MessageResponse> messages = messageService.getAllMessagesByRoomId(roomId);
        return new ResponseEntity<>(messages, HttpStatus.OK);

    }

}
