package com.devjoliveira.appointment_management_api.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devjoliveira.appointment_management_api.dto.CustomerDTO;
import com.devjoliveira.appointment_management_api.dto.CustomerMinDTO;
import com.devjoliveira.appointment_management_api.service.CustomerService;

import jakarta.validation.Valid;

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

  @PostMapping
  public ResponseEntity<CustomerDTO> save(@RequestBody @Valid CustomerMinDTO request) {

    CustomerDTO customerDTO = customerService.save(request);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(customerDTO.id()).toUri();

    return ResponseEntity.created(uri).body(customerDTO);

  }

  @PutMapping("/{id}")
  public ResponseEntity<CustomerDTO> change(@PathVariable UUID id, @RequestBody @Valid CustomerMinDTO request) {
    return ResponseEntity.ok().body(customerService.change(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    customerService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
