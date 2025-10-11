package com.example.sentimentchat.repository;

import com.example.sentimentchat.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {

    // Custom query method to find messages by room ID
    List<Message> findByRoomId(UUID roomId);
}
