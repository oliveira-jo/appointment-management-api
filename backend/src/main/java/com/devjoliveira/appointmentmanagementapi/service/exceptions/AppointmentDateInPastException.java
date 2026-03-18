package com.devjoliveira.appointmentmanagementapi.service.exceptions;

public class AppointmentDateInPastException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public AppointmentDateInPastException(String message) {
    super(message);
  }

}