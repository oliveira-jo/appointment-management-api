package com.devjoliveira.appointment_management_api.dto;

import java.math.BigDecimal;
import java.time.Duration;

import com.devjoliveira.appointment_management_api.domain.Product;

public record ProductMinDTO(
        String name,
        Duration duration,
        BigDecimal price) {

    public ProductMinDTO(Product entity) {
        this(entity.getName(), entity.getDuration(), entity.getPrice());
    }
}
