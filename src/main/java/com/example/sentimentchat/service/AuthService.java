package com.example.sentimentchat.service;

import com.example.sentimentchat.dtos.auth.LoginRequest;
import com.example.sentimentchat.dtos.auth.LoginResponse;
import com.example.sentimentchat.dtos.auth.SignupRequest;
import com.example.sentimentchat.dtos.auth.SignupResponse;
import com.example.sentimentchat.entity.User;
import com.example.sentimentchat.exception.PasswordDoesNotMatchException;
import com.example.sentimentchat.exception.UserDoesNotExistException;
import com.example.sentimentchat.exception.UsernameAlreadyExistsException;
import com.example.sentimentchat.repository.UserRepository;
import com.example.sentimentchat.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest authRequest) {

        String username = authRequest.username();

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UserDoesNotExistException();
        }
        if (!passwordEncoder.matches(authRequest.password(), user.getPassword())) {
            throw new PasswordDoesNotMatchException("Password does not match");
        }

        String token = jwtUtil.generateToken(username);

        return new LoginResponse(token, username, user.getId());
    }

    public SignupResponse signup(SignupRequest signupRequest) {

        User existingUser = userRepository.findByUsername(signupRequest.username());
        if (existingUser != null) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        User newUser = new User();
        newUser.setUsername(signupRequest.username());
        newUser.setPassword(passwordEncoder.encode(signupRequest.password()));
        newUser.setBio(signupRequest.bio());
        newUser.setProfileImage(signupRequest.profileImage());

        User savedUser = userRepository.save(newUser);

        return new SignupResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getBio(),
                savedUser.getProfileImage()
        );
    }
}
