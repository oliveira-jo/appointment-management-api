package com.devjoliveira.appointment_management_api.dto;

import com.devjoliveira.appointment_management_api.domain.Customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerMinDTO(
                @NotBlank(message = "Name is required") String name,
                @NotBlank(message = "Phone is required") String phone,
                @NotBlank(message = "Email is required") @Email String email) {

        public CustomerMinDTO(Customer customer) {
                this(customer.getName(), customer.getPhone(), customer.getEmail());
        }
}
