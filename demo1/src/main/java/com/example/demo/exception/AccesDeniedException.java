package com.example.demo.exception;

public class AccesDeniedException extends BaseException{
    public AccesDeniedException(String message) {
        super(String.format("Access is denied"));
    }

}
