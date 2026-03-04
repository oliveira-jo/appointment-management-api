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

import com.devjoliveira.appointment_management_api.dto.ProfessionalDTO;
import com.devjoliveira.appointment_management_api.dto.ProfessionalMinDTO;
import com.devjoliveira.appointment_management_api.service.ProfessionalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/professionals")
public class ProfessionalController {

  private final ProfessionalService professionalService;

  public ProfessionalController(ProfessionalService professionalService) {
    this.professionalService = professionalService;
  }

  @GetMapping
  public ResponseEntity<List<ProfessionalMinDTO>> findAll() {
    return ResponseEntity.ok().body(professionalService.findAll());
  }

  @GetMapping("/{email}")
  public ResponseEntity<ProfessionalDTO> findByEmail(@PathVariable String email) {
    return ResponseEntity.ok().body(professionalService.findByEmail(email));
  }

  @PostMapping
  public ResponseEntity<ProfessionalDTO> save(@RequestBody @Valid ProfessionalMinDTO request) {

    ProfessionalDTO professionalDTO = professionalService.save(request);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(professionalDTO.id()).toUri();

    return ResponseEntity.created(uri).body(professionalDTO);

  }

  @PutMapping("/{id}")
  public ResponseEntity<ProfessionalDTO> change(@PathVariable UUID id, @RequestBody @Valid ProfessionalMinDTO request) {
    return ResponseEntity.ok().body(professionalService.change(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    professionalService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
