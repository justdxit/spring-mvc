package com.moodeon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    //500에러
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e){
        return "error/server_error";
    }

    //404에러
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handelNotFound(NoHandlerFoundException e) {
        return "error/notfound";
    }
}
