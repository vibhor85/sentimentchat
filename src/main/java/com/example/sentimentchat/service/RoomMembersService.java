package com.example.sentimentchat.service;

import com.example.sentimentchat.dtos.roomMember.RoomMemberResponse;
import com.example.sentimentchat.entity.Room;
import com.example.sentimentchat.entity.RoomMembers;
import com.example.sentimentchat.entity.User;
import com.example.sentimentchat.enums.Role;
import com.example.sentimentchat.repository.RoomMembersRepository;
import com.example.sentimentchat.repository.RoomRepository;
import com.example.sentimentchat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomMembersService {

    private final RoomMembersRepository roomMembersRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @Transactional
    public RoomMemberResponse addMemberToRoom(UUID roomId, UUID userId, boolean isAdmin) {

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + roomId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        RoomMembers roomMembers = new RoomMembers();
        roomMembers.setRoom(room);
        roomMembers.setUser(user);
        roomMembers.setRole(isAdmin ? Role.ADMIN : Role.MEMBER);

        RoomMembers savedRoomMember = roomMembersRepository.save(roomMembers);

        return toResponse(savedRoomMember);

    }

    public List<RoomMembers> getRoomMembersByRoomId(UUID roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + roomId));


        return roomMembersRepository.findByRoomId(roomId);
    }


    private RoomMemberResponse toResponse(RoomMembers roomMembers) {
        return new RoomMemberResponse(
                roomMembers.getId(),
                roomMembers.getRoom().getId(),
                roomMembers.getUser().getId(),
                roomMembers.getRole(),
                roomMembers.getJoinedAt()
        );
    }

}
