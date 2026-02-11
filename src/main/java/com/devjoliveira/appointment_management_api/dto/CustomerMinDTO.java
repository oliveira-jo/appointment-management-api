package com.devjoliveira.appointment_management_api.dto;

import com.devjoliveira.appointment_management_api.domain.Customer;

public record CustomerMinDTO(
                String name,
                String phone,
                String email) {

        public CustomerMinDTO(Customer customer) {
                this(customer.getName(), customer.getPhone(), customer.getEmail());
        }
}
