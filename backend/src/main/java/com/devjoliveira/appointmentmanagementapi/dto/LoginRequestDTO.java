package com.devjoliveira.appointmentmanagementapi.dto;

public record LoginRequestDTO(
        String email,
        String password) {
}