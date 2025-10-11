package com.example.sentimentchat.service;

import com.example.sentimentchat.dtos.room.*;
import com.example.sentimentchat.entity.Room;
import com.example.sentimentchat.entity.RoomMembers;
import com.example.sentimentchat.entity.User;
import com.example.sentimentchat.enums.RoomType;
import com.example.sentimentchat.exception.UserDoesNotExistException;
import com.example.sentimentchat.repository.RoomRepository;
import com.example.sentimentchat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    private final UserRepository userRepository;

    private final RoomMembersService roomMembersService;

    public RoomResponse createRoom(RoomRequest roomRequest) {

        System.out.println("Creating room with request: " + roomRequest);

        User user = userRepository.findById(roomRequest.createdBy())
                .orElseThrow(() -> new UserDoesNotExistException("User not found with id: " + roomRequest.createdBy()));

        Room room = new com.example.sentimentchat.entity.Room();
        room.setName(roomRequest.name());
        room.setDescription(roomRequest.description());
        room.setRoomType(roomRequest.roomType());
        room.setPrivate(roomRequest.isPrivate());
        room.setCreatedBy(user);

        Room savedRoom = roomRepository.save(room);

        List<Member> members = new ArrayList<>();
        members.add(new Member(user.getId(), user.getUsername()));

        roomMembersService.addMemberToRoom(savedRoom.getId(), user.getId(), true);

        return toResponse(savedRoom, members);
    }

    public RoomResponse createDirectRoom(UUID userId1, UUID userId2) {

        User user1 = userRepository.findById(userId1)
                .orElseThrow(() -> new UserDoesNotExistException("User not found with id: " + userId1));

        User user2 = userRepository.findById(userId2)
                .orElseThrow(() -> new UserDoesNotExistException("User not found with id: " + userId2));

        Room room = new Room();
        room.setName("");
        room.setDescription("Direct Room between " + user1.getUsername() + " and " + user2.getUsername());
        room.setRoomType(RoomType.DIRECT);
        room.setPrivate(true);
        room.setCreatedBy(user1);





        Room savedRoom = roomRepository.save(room);

        roomMembersService.addMemberToRoom(savedRoom.getId(), user1.getId(), false);
        roomMembersService.addMemberToRoom(savedRoom.getId(), user2.getId(),false);

        List<Member> roomMembers = new ArrayList<>();
        roomMembers.add(new Member(user1.getId(), user1.getUsername()));
        roomMembers.add(new Member(user2.getId(), user2.getUsername()));

        return new RoomResponse(
                savedRoom.getId(),
                savedRoom.getName(),
                savedRoom.getDescription(),
                savedRoom.getRoomType(),
                savedRoom.isPrivate(),
                savedRoom.getCreatedBy().getId(),
                roomMembers,
                savedRoom.getCreatedAt(),
                savedRoom.getUpdatedAt()
        );
    }

    public List<RoomResponse> getAllByCreatedByUser(UUID userId) {

        List<Room> rooms = roomRepository.findAllByCreatedById(userId);

        if(rooms.isEmpty()) {
            throw new UserDoesNotExistException("No rooms found for user with id: " + userId);
        }


        return rooms.stream().map(room -> {
            List<RoomMembers> roomMembers = roomMembersService.getRoomMembersByRoomId(room.getId());
            List<Member> members = roomMembers.stream().map(rm -> new Member(rm.getUser().getId(), rm.getUser().getUsername())).toList();
            return toResponse(room, members);
        }).toList();

    }

    public List<RoomResponse> getAllRoomsForUser(UUID userId) {
        // Fetch rooms where the user is a member
        List<Room> rooms = roomRepository.findAllRoomsByUserId(userId);



        if (rooms.isEmpty()) {
            throw new UserDoesNotExistException("No rooms found for user with id: " + userId);
        }

        // Map each room to its response object, including member details
        return rooms.stream().map(room -> {
            List<RoomMembers> roomMembers = roomMembersService.getRoomMembersByRoomId(room.getId());
            List<Member> members = roomMembers.stream().map(rm -> new Member(rm.getUser().getId(), rm.getUser().getUsername())).toList();
            return toResponse(room, members);
        }).toList();
    }

    private RoomResponse toResponse(Room room, List<Member> members) {

        return new RoomResponse(
                room.getId(),
                room.getName(),
                room.getDescription(),
                room.getRoomType(),
                room.isPrivate(),
                room.getCreatedBy().getId(),
                members,
                room.getCreatedAt(),
                room.getUpdatedAt()
        );
    }

}
