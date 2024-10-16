package com.example.finalproject.exception;

public class SellerNotFoundException extends BaseException {

        public SellerNotFoundException(String message) {
            super(String.format("Seller with id %s not found", message));
        }

        public SellerNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }
}
