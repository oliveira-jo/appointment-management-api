package com.devjoliveira.appointment_management_api.dto;

import java.time.LocalDateTime;

import com.devjoliveira.appointment_management_api.domain.Appointment;

public record AppointmentDTO(
    String id,
    CustomerDTO customerDTO,
    ProfessionalDTO professionalDTO,
    ProductDTO productDTO,
    LocalDateTime scheduledAt,
    String status) {

  public AppointmentDTO(Appointment entity) {
    this(entity.getId().toString(), new CustomerDTO(entity.getCustomer()),
        new ProfessionalDTO(entity.getProfessional()), new ProductDTO(entity.getProduct()),
        entity.getScheduledAt(), entity.getStatus().name());
  }

}
