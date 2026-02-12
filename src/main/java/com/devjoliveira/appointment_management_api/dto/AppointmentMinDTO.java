package com.devjoliveira.appointment_management_api.dto;

import java.time.LocalDateTime;

import com.devjoliveira.appointment_management_api.domain.Appointment;

import jakarta.validation.constraints.NotBlank;

public record AppointmentMinDTO(
                @NotBlank String customerUUID,
                @NotBlank String professionalUUID,
                @NotBlank String productUUID,
                @NotBlank LocalDateTime scheduledAt,
                String status) {

        public AppointmentMinDTO(Appointment entity) {
                this(entity.getCustomer().getId().toString(), entity.getProfessional().getId().toString(),
                                entity.getProduct().getId().toString(), entity.getScheduledAt(),
                                entity.getStatus().toString());
        }

}
