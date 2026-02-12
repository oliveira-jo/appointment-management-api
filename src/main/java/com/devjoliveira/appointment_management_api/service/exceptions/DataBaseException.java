package com.devjoliveira.appointment_management_api.service.exceptions;

public class DataBaseException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public DataBaseException(String message) {
    super(message);
  }

}