package com.devjoliveira.appointment_management_api.dto;

import java.util.UUID;

import com.devjoliveira.appointment_management_api.domain.User;

public record UserDTO(
                UUID id,
                String name,
                String phone,
                String email) {

        public UserDTO(User customer) {
                this(customer.getId(), customer.getName(), customer.getPhone(), customer.getEmail());
        }
}
