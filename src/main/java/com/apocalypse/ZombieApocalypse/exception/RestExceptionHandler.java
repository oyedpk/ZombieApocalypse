package com.apocalypse.ZombieApocalypse.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    //other exception handlers


    @ExceptionHandler(InvalidRequestException.class)
    protected ResponseEntity<String> handleEntityNotFound(
            InvalidRequestException ex) {
        InvalidRequestException apiError = new InvalidRequestException(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<String> buildResponseEntity(InvalidRequestException apiError) {
        return new ResponseEntity<>(apiError.getMessage(), apiError.getStatus());
    }
}
