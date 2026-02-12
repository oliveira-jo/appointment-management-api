package com.devjoliveira.appointment_management_api.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.devjoliveira.appointment_management_api.domain.Appointment;

public record AppointmentDTO(
                UUID id,
                String customerName,
                String professionalName,
                String productName,
                LocalDateTime scheduledAt,
                String status) {

        public AppointmentDTO(Appointment entity) {
                this(entity.getId(), entity.getCustomer().getName(),
                                entity.getProfessional().getName(),
                                entity.getProduct().getName(), entity.getScheduledAt(),
                                entity.getStatus().toString());
        }

}
