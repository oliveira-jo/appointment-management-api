package com.devjoliveira.appointment_management_api.dto;

import com.devjoliveira.appointment_management_api.domain.Professional;

public record ProfessionalMinDTO(
                String name,
                String specialty,
                String email) {

        public ProfessionalMinDTO(Professional professional) {
                this(professional.getName(), professional.getSpecialty(), professional.getEmail());
        }
}
