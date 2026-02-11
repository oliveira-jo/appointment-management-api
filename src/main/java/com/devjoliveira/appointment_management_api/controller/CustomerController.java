package com.devjoliveira.appointment_management_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devjoliveira.appointment_management_api.dto.CustomerDTO;
import com.devjoliveira.appointment_management_api.dto.CustomerMinDTO;
import com.devjoliveira.appointment_management_api.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping
  public ResponseEntity<List<CustomerMinDTO>> findAll() {
    return ResponseEntity.ok().body(customerService.findAll());
  }

  @GetMapping("/{email}")
  public ResponseEntity<CustomerDTO> findByEmail(@PathVariable String email) {
    return ResponseEntity.ok().body(customerService.findByEmail(email));
  }

}
