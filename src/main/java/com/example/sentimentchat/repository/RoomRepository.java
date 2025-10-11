package com.example.sentimentchat.repository;

import com.example.sentimentchat.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {

    List<Room> findAllByCreatedById(UUID userId);

    @Query("SELECT r FROM Room r JOIN FETCH r.roomMembers rm WHERE rm.user.id = :userId")
    List<Room> findAllRoomsByUserId(@Param("userId") UUID userId);
}
