package com.devjoliveira.appointmentmanagementapi.service.exceptions;

public class ProfessionalResourceNotAvaliable extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ProfessionalResourceNotAvaliable(String message) {
    super(message);
  }

}