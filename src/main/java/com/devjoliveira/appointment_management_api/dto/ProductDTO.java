package com.devjoliveira.appointment_management_api.dto;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.UUID;

import com.devjoliveira.appointment_management_api.domain.Product;

public record ProductDTO(
    UUID id,
    String name,
    Duration duration,
    BigDecimal price) {

  public ProductDTO(Product product) {
    this(product.getId(), product.getName(), product.getDuration(), product.getPrice());
  }

}
