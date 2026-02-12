package com.devjoliveira.appointment_management_api.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.devjoliveira.appointment_management_api.domain.Appointment;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;

public record AppointmentMinDTO(
                UUID id,
                @NotBlank String customerUUID,
                @NotBlank String professionalUUID,
                @NotBlank String productUUID,
                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime scheduledAt,
                String status) {

        public AppointmentMinDTO(Appointment entity) {
                this(entity.getId(), entity.getCustomer().getId().toString(),
                                entity.getProfessional().getId().toString(),
                                entity.getProduct().getId().toString(), entity.getScheduledAt(),
                                entity.getStatus().toString());
        }

}
