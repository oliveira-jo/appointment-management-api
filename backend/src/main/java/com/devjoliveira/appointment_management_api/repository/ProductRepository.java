package com.devjoliveira.appointment_management_api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devjoliveira.appointment_management_api.domain.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {

  Optional<Product> findByName(String name);

}
