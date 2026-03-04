package com.devjoliveira.appointment_management_api.dto;

import java.util.UUID;

import com.devjoliveira.appointment_management_api.domain.Professional;

public record ProfessionalDTO(
                UUID id,
                String name,
                String specialty,
                String email) {

        public ProfessionalDTO(Professional professional) {
                this(professional.getId(), professional.getName(), professional.getSpecialty(),
                                professional.getEmail());
        }
}
