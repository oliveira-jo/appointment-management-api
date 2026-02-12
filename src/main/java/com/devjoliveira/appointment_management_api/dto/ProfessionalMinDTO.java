package com.devjoliveira.appointment_management_api.dto;

import com.devjoliveira.appointment_management_api.domain.Professional;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ProfessionalMinDTO(
                @NotBlank(message = "Name is required") String name,
                @NotBlank(message = "Specialty is required") String specialty,
                @NotBlank(message = "Email is required") @Email String email) {

        public ProfessionalMinDTO(Professional professional) {
                this(professional.getName(), professional.getSpecialty(), professional.getEmail());
        }
}
