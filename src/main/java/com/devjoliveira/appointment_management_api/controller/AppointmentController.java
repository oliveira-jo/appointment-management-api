package com.devjoliveira.appointment_management_api.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devjoliveira.appointment_management_api.dto.AppointmentDTO;
import com.devjoliveira.appointment_management_api.dto.AppointmentMinDTO;
import com.devjoliveira.appointment_management_api.service.AppointmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

  private final AppointmentService appointmentService;

  public AppointmentController(AppointmentService appointmentService) {
    this.appointmentService = appointmentService;
  }

  @GetMapping
  public ResponseEntity<Page<AppointmentMinDTO>> findAll(Pageable pageable) {
    return ResponseEntity.ok().body(appointmentService.findAllPaged(pageable));
  }

  @PostMapping
  public ResponseEntity<AppointmentDTO> save(@RequestBody @Valid AppointmentMinDTO request) {

    AppointmentDTO appointmentDTO = appointmentService.createAppointment(request.customerUUID(),
        request.professionalUUID(),
        request.productUUID(), request.scheduledAt());

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(appointmentDTO.id()).toUri();

    return ResponseEntity.created(uri).body(appointmentDTO);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    appointmentService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
