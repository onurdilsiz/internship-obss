package com.example.demo.exception;

import com.example.demo.common.Constants;
import com.example.demo.dto.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<BaseResponse>handleException(Exception e ){
        return new ResponseEntity<>(new BaseResponse<>(Constants.ResponseCodes.UNKNOWN_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<BaseResponse>handleUserNotFoundException(UserNotFoundException e ){
        return new ResponseEntity<>(new BaseResponse<>(Constants.ResponseCodes.USER_NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = AccesDeniedException.class)
    public ResponseEntity<BaseResponse>handleAccessDeniedException(UserNotFoundException e ){
        return new ResponseEntity<>(new BaseResponse<>(Constants.ResponseCodes.ACCESS_DENIED, e.getMessage()), HttpStatus.FORBIDDEN);
    }
}
