package com.jloroz.blogrestapi.exception;

import org.springframework.http.HttpStatus;

public class BlogApiException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public BlogApiException(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }
    public BlogApiException(String message, HttpStatus status, String customMessage){
        super(message);
        this.status = status;
        this.message = customMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
