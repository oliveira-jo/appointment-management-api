package com.devjoliveira.appointment_management_api.service;

import java.util.List;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devjoliveira.appointment_management_api.domain.User;
import com.devjoliveira.appointment_management_api.dto.UserDTO;
import com.devjoliveira.appointment_management_api.dto.UserMinDTO;
import com.devjoliveira.appointment_management_api.enums.UserRole;
import com.devjoliveira.appointment_management_api.repository.UserRepository;
import com.devjoliveira.appointment_management_api.service.exceptions.DatabaseException;
import com.devjoliveira.appointment_management_api.service.exceptions.ResourceNotFoundException;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository customerRepository) {
    this.userRepository = customerRepository;
  }

  @Transactional(readOnly = true)
  public List<UserMinDTO> findAll() {
    return userRepository.findAll().stream().map(UserMinDTO::new).toList();
  }

  @Transactional(readOnly = true)
  public UserDTO findByEmail(String email) {
    return userRepository.findByEmail(email).map(UserDTO::new).orElseThrow(
        () -> new ResourceNotFoundException("Customer not found with email: " + email));
  }

  @Transactional
  public UserDTO save(UserMinDTO request, UserRole role) {
    userRepository.findByEmail(request.email()).ifPresent(customer -> {
      throw new DuplicateKeyException("Customer with email " + customer.getEmail() + " already exists");
    });

    User entity = new User();
    entity.setName(request.name());
    entity.setPhone(request.phone());
    entity.setEmail(request.email());
    entity.setRole(role);

    var fromDB = userRepository.save(entity);
    return new UserDTO(fromDB);
  }

  @Transactional
  public UserDTO change(UUID id, UserMinDTO request) {

    var fromDB = userRepository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException("User with id " + id + " not found"));

    if (!fromDB.getEmail().equals(request.email())) {
      userRepository.findByEmail(request.email()).ifPresent(customer -> {
        throw new DuplicateKeyException("User with email " + customer.getEmail() + " already exists");
      });
    }

    fromDB.setName(request.name());
    fromDB.setPhone(request.phone());
    fromDB.setEmail(request.email());

    var updatedCustomer = userRepository.save(fromDB);
    return new UserDTO(updatedCustomer);
  }

  @Transactional
  public void delete(UUID id) {
    var fromDB = userRepository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException("Customer with id " + id + " not found"));

    try {
      userRepository.delete(fromDB);
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException("Fail in reference integrity");
    }

  }

}
