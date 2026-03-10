package com.devjoliveira.appointmentmanagementapi.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devjoliveira.appointmentmanagementapi.domain.User;

public interface UserRepository extends JpaRepository<User, UUID> {

	Optional<User> findByEmail(String email);

	@Query("SELECT u FROM User u WHERE u.role = 'ROLE_PROFESSIONAL'")
	Optional<List<User>> findAllProfessionals();

	@Query("SELECT u FROM User u WHERE u.role = 'ROLE_CUSTOMER'")
	Optional<List<User>> findAllCustomers();

	User findByName(String username);

}