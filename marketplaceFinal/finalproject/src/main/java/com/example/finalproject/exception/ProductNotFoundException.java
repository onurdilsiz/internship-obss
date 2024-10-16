package com.example.finalproject.exception;

public class ProductNotFoundException extends BaseException {
    public ProductNotFoundException(String message) {
        super(String.format("product with id"+message+" not found"));
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
