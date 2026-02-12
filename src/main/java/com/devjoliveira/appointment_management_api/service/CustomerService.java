package com.devjoliveira.appointment_management_api.service;

import java.util.List;
import java.util.UUID;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devjoliveira.appointment_management_api.domain.Customer;
import com.devjoliveira.appointment_management_api.dto.CustomerDTO;
import com.devjoliveira.appointment_management_api.dto.CustomerMinDTO;
import com.devjoliveira.appointment_management_api.repository.CustomerRepository;
import com.devjoliveira.appointment_management_api.service.exceptions.ResourceNotFoundException;

@Service
public class CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Transactional(readOnly = true)
  public List<CustomerMinDTO> findAll() {
    return customerRepository.findAll().stream().map(CustomerMinDTO::new).toList();
  }

  @Transactional(readOnly = true)
  public CustomerDTO findByEmail(String email) {
    return customerRepository.findByEmail(email).map(CustomerDTO::new).orElseThrow(
        () -> new ResourceNotFoundException("Customer not found with email: " + email));
  }

  @Transactional
  public CustomerDTO save(CustomerMinDTO request) {
    customerRepository.findByEmail(request.email()).ifPresent(customer -> {
      throw new DuplicateKeyException("Customer with email " + customer.getEmail() + " already exists");
    });

    Customer customer = new Customer();
    customer.setName(request.name());
    customer.setPhone(request.phone());
    customer.setEmail(request.email());

    var fromDB = customerRepository.save(customer);
    return new CustomerDTO(fromDB);
  }

  @Transactional
  public CustomerDTO change(UUID id, CustomerMinDTO request) {

    var fromDB = customerRepository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException("Customer with id " + id + " not found"));

    if (!fromDB.getEmail().equals(request.email())) {
      customerRepository.findByEmail(request.email()).ifPresent(customer -> {
        throw new DuplicateKeyException("Customer with email " + customer.getEmail() + " already exists");
      });
    }

    fromDB.setName(request.name());
    fromDB.setPhone(request.phone());
    fromDB.setEmail(request.email());

    var updatedCustomer = customerRepository.save(fromDB);
    return new CustomerDTO(updatedCustomer);
  }

  @Transactional
  public void delete(UUID id) {
    var fromDB = customerRepository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException("Customer with id " + id + " not found"));

    customerRepository.delete(fromDB);
  }

}
