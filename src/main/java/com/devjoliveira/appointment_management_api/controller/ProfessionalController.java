package com.devjoliveira.appointment_management_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devjoliveira.appointment_management_api.dto.ProfessionalDTO;
import com.devjoliveira.appointment_management_api.dto.ProfessionalMinDTO;
import com.devjoliveira.appointment_management_api.service.ProfessionalService;

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

}
