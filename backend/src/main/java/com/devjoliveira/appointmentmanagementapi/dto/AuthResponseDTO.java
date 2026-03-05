package com.devjoliveira.appointmentmanagementapi.dto;

import java.util.UUID;

public record AuthResponseDTO(
                UUID id,
                String email,
                String accessToken,
                Long expiresIn) {

}