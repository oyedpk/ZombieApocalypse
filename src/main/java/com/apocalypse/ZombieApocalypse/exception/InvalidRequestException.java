package com.apocalypse.ZombieApocalypse.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class InvalidRequestException extends Throwable {

    private HttpStatus status;
    private String message;
    private LocalDateTime timestamp;

    private InvalidRequestException() {
        timestamp = LocalDateTime.now();
    }

    InvalidRequestException(HttpStatus status) {
        this();
        this.status = status;
    }

    public InvalidRequestException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
