package com.example.finalproject.exception;

public class UserNotFoundException extends BaseException{
    public UserNotFoundException(String message) {
        super(String.format("user with id"+message+" not found"));
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
