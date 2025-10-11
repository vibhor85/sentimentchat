package com.example.sentimentchat.repository;

import com.example.sentimentchat.entity.RoomMembers;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomMembersRepository extends JpaRepository<RoomMembers, UUID> {
    List<RoomMembers> findByRoomId(UUID roomId);
}
