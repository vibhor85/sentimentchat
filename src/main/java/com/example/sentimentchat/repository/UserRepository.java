package com.example.sentimentchat.repository;

import com.example.sentimentchat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);

    List<User> findByUsernameStartingWithIgnoreCase(String username);
}
