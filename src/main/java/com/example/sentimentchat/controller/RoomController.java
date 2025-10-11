package com.example.sentimentchat.controller;

import com.example.sentimentchat.dtos.room.DirectRoomRequest;
import com.example.sentimentchat.dtos.room.DirectRoomResponse;
import com.example.sentimentchat.dtos.room.RoomRequest;
import com.example.sentimentchat.dtos.room.RoomResponse;
import com.example.sentimentchat.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/create")
    public ResponseEntity<RoomResponse> createRoom(@RequestBody RoomRequest roomRequest) {

        RoomResponse roomResponse = roomService.createRoom(roomRequest);

        return new ResponseEntity<>(roomResponse, HttpStatus.CREATED);

    }

    @PostMapping("/direct")
    public ResponseEntity<RoomResponse> createDirectRoom(@RequestBody DirectRoomRequest roomRequest) {

        RoomResponse roomResponse = roomService.createDirectRoom(roomRequest.userId1(), roomRequest.userId2());

        return new ResponseEntity<>(roomResponse, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<RoomResponse>> getAllRoomsForUser(@RequestParam(required = true) UUID userId) {
        List<RoomResponse> rooms = roomService.getAllRoomsForUser(userId);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }
}
