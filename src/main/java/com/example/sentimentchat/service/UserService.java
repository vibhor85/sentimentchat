package com.example.sentimentchat.service;

import com.example.sentimentchat.dtos.UserResponse;
import com.example.sentimentchat.entity.User;
import com.example.sentimentchat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponse> SearchByUsername(String username) {

        List<User> users = userRepository.findByUsernameStartingWithIgnoreCase(username);

        return users.stream().map(this::toResponse).toList();
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getBio(),
                user.getProfileImage(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
