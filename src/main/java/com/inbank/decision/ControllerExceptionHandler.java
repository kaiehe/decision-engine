package com.inbank.decision;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = CustomRestControllerAdvice.class)
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<com.inbank.decision.ErrorResponse> inputArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("Check input parameters, validation failed: {}", e.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder()
                .error(Error.builder()
                        .description("Check input parameters")
                        .errorCode(400)
                        .message(e.getMessage())
                        .build())
                .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> requestFailedException(RuntimeException e) {
        log.error("RuntimeException: {}", e.getMessage(), e);
        return new ResponseEntity<>(ErrorResponse.builder()
                .error(Error.builder()
                        .description("Request failed")
                        .errorCode(500)
                        .message(e.getMessage())
                        .build())
                .build(),
                HttpStatus.BAD_REQUEST);
    }

}
