package com.aladdin.demobooksite.exceptions;

import com.aladdin.demobooksite.model.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleException(RuntimeException ex, WebRequest request) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");
        String formattedDate = now.format(formatter);

        ErrorResponse errorResponse = new ErrorResponse
                (HttpStatus.BAD_REQUEST.value(),
                        ex.getMessage(),
                        request.getDescription(false),
                        formattedDate
                );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
