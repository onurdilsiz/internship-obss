package com.example.demo.exception;

public class RoleNotFoundException extends BaseException{
    public RoleNotFoundException(String message) {
        super(String.format("Role with name %s not found", message));
    }
}
