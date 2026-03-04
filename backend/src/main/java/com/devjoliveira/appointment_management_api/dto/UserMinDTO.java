package com.devjoliveira.appointment_management_api.dto;

import com.devjoliveira.appointment_management_api.domain.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserMinDTO(
                @NotBlank(message = "Name is required") String name,
                @NotBlank(message = "Phone is required") String phone,
                @NotBlank(message = "Email is required") @Email String email,
                String role) {

        public UserMinDTO(User customer) {
                this(customer.getName(), customer.getPhone(), customer.getEmail(), customer.getRole().toString());
        }
}
