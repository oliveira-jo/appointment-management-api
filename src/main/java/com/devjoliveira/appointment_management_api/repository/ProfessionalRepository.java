package com.devjoliveira.appointment_management_api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devjoliveira.appointment_management_api.domain.Professional;

public interface ProfessionalRepository extends JpaRepository<Professional, UUID> {

  Optional<Professional> findByEmail(String email);

}
