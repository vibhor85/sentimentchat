package com.example.sentimentchat.controller;

import com.example.sentimentchat.dtos.UserResponse;
import com.example.sentimentchat.repository.UserRepository;
import com.example.sentimentchat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/info")
    public String getUserInfo() {
        return "User info";
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> searchUserByUsername(@RequestParam String username) {

        List<UserResponse> users = userService.SearchByUsername(username);

        // Implementation here
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
