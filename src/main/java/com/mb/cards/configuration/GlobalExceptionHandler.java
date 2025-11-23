package com.mb.cards.configuration;

import com.mb.cards.dto.ApiErrResponseDTO;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle database-related exceptions
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ApiErrResponseDTO> handleDatabaseException(DataAccessException ex, HttpServletRequest request) {
        String message = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();
        ApiErrResponseDTO error = new ApiErrResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Database Error",
                message,
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // Handle all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrResponseDTO> handleGenericException(Exception ex, HttpServletRequest request) {
        ApiErrResponseDTO error = new ApiErrResponseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}

