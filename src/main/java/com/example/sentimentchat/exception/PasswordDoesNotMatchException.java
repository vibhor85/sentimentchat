package com.example.sentimentchat.exception;

public class PasswordDoesNotMatchException extends RuntimeException{

    public PasswordDoesNotMatchException(String message) {
        super(message);
    }
}
