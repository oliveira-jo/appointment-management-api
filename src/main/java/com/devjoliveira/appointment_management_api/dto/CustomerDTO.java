package com.devjoliveira.appointment_management_api.dto;

import java.util.UUID;

import com.devjoliveira.appointment_management_api.domain.Customer;

public record CustomerDTO(
                UUID id,
                String name,
                String phone,
                String email) {

        public CustomerDTO(Customer customer) {
                this(customer.getId(), customer.getName(), customer.getPhone(), customer.getEmail());
        }
}
