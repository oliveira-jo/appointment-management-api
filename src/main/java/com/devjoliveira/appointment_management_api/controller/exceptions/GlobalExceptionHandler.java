package com.devjoliveira.appointment_management_api.controller.exceptions;

import java.time.Instant;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devjoliveira.appointment_management_api.service.exceptions.DatabaseException;
import com.devjoliveira.appointment_management_api.service.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<StandartError> entityNotFoundException(ResourceNotFoundException e,
      HttpServletRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    StandartError error = new StandartError(
        Instant.now(),
        status.value(),
        "Resource not found.",
        e.getMessage(),
        request.getRequestURI());
    return ResponseEntity.status(status).body(error);
  }

  @ExceptionHandler(DatabaseException.class)
  public ResponseEntity<StandartError> databaseException(DatabaseException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    StandartError error = new StandartError(
        Instant.now(),
        status.value(),
        "Database exception.",
        e.getMessage(),
        request.getRequestURI());
    return ResponseEntity.status(status).body(error);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<StandartError> dataIntegrityViolationException(DataIntegrityViolationException e,
      HttpServletRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    StandartError error = new StandartError(
        Instant.now(),
        status.value(),
        "Database exception.",
        e.getMessage(),
        request.getRequestURI());
    return ResponseEntity.status(status).body(error);
  }

}
