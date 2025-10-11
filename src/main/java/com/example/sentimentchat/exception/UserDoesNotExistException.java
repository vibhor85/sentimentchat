package com.example.sentimentchat.exception;

public class UserDoesNotExistException extends RuntimeException{

    public UserDoesNotExistException() {}

    public UserDoesNotExistException(String message) {
        super(message);
    }
}
