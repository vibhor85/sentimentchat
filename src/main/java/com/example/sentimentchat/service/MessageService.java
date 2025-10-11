package com.example.sentimentchat.service;

import com.example.sentimentchat.dtos.message.MessageRequest;
import com.example.sentimentchat.dtos.message.MessageResponse;
import com.example.sentimentchat.entity.Message;
import com.example.sentimentchat.entity.Room;
import com.example.sentimentchat.entity.User;
import com.example.sentimentchat.exception.UserDoesNotExistException;
import com.example.sentimentchat.repository.MessageRepository;
import com.example.sentimentchat.repository.RoomRepository;
import com.example.sentimentchat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public List<MessageResponse> getAllMessagesByRoomId(UUID roomId) {
        List<Message> messages = messageRepository.findByRoomId(roomId);

        return messages.stream()
                .map(this::toResponse)
                .toList();

    }

    @Transactional
    public MessageResponse saveMessage(MessageRequest messageRequest, UUID roomId) {
        Message message = new Message();
        message.setContent(messageRequest.content());

        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not" +
                " " +
                "found"));
        User sender =
                userRepository.findById(messageRequest.senderId()).orElseThrow(() -> new UserDoesNotExistException(
                        "User not found"));
        message.setRoom(room);
        message.setSender(sender);
        Message savedMessage = messageRepository.save(message);

        return toResponse(savedMessage);
    }

    private MessageResponse toResponse(Message message) {
        return new MessageResponse(
                message.getId().toString(),
                message.getRoom().getId(),
                message.getSender().getId(),
                message.getContent(),
                message.getEditedAt() != null ? message.getEditedAt().toString() : null,
                message.getCreatedAt().toString()
        );
    }
}
