package com.devjoliveira.appointmentmanagementapi.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devjoliveira.appointmentmanagementapi.domain.User;
import com.devjoliveira.appointmentmanagementapi.enums.UserRole;

public interface UserRepository extends JpaRepository<User, UUID> {

	Optional<User> findByEmail(String email);

	Page<User> findByRole(UserRole role, Pageable pageable);

	List<User> findByRole(UserRole role);

	User findByName(String username);

}