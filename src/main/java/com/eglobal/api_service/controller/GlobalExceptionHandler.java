package com.eglobal.api_service.controller;

import com.eglobal.api_service.exception.InvalidApiKeyException;
import com.eglobal.api_service.exception.UpdateFailedException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR = "error";

    @ExceptionHandler(InvalidApiKeyException.class)
    public ResponseEntity<Map<String, String>> handleInvalidApiKey(InvalidApiKeyException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Map.of(ERROR, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneral(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(ERROR, "Error inesperado", "detalle", ex.getMessage()));
    }

    @ExceptionHandler(UpdateFailedException.class)
    public ResponseEntity<Map<String, String>> handleUpdateFailed(UpdateFailedException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of(ERROR, ex.getMessage()));
    }
}
