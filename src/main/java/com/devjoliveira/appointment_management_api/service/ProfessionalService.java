package com.devjoliveira.appointment_management_api.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devjoliveira.appointment_management_api.domain.Professional;
import com.devjoliveira.appointment_management_api.dto.ProfessionalDTO;
import com.devjoliveira.appointment_management_api.dto.ProfessionalMinDTO;
import com.devjoliveira.appointment_management_api.repository.ProfessionalRepository;

@Service
public class ProfessionalService {

  private final ProfessionalRepository professionalRepository;

  public ProfessionalService(ProfessionalRepository professionalRepository) {
    this.professionalRepository = professionalRepository;
  }

  @Transactional(readOnly = true)
  public List<ProfessionalMinDTO> findAll() {
    return professionalRepository.findAll().stream().map(ProfessionalMinDTO::new).toList();
  }

  @Transactional(readOnly = true)
  public ProfessionalDTO findByEmail(String email) {
    return professionalRepository.findByEmail(email).map(ProfessionalDTO::new).orElseThrow(
        () -> new RuntimeException("Professional not found with email: " + email));
  }

  @Transactional
  public ProfessionalDTO save(ProfessionalMinDTO request) {
    professionalRepository.findByEmail(request.email()).orElseThrow(
        () -> new RuntimeException("Professional with email " + request.email() + " already exists"));

    Professional customer = new Professional();
    customer.setName(request.name());
    customer.setSpecialty(request.specialty());
    customer.setEmail(request.email());

    var fromDB = professionalRepository.save(customer);
    return new ProfessionalDTO(fromDB);
  }

  @Transactional
  public ProfessionalDTO change(ProfessionalMinDTO request) {
    var fromDB = professionalRepository.findByEmail(request.email()).orElseThrow(
        () -> new RuntimeException("Professional with email " + request.email() + " not found"));

    fromDB.setName(request.name());
    fromDB.setSpecialty(request.specialty());
    fromDB.setEmail(request.email());

    var updateProfessional = professionalRepository.save(fromDB);
    return new ProfessionalDTO(updateProfessional);
  }

  @Transactional
  public void delete(UUID id) {
    var fromDB = professionalRepository.findById(id).orElseThrow(
        () -> new RuntimeException("Professional with id " + id + " not found"));

    professionalRepository.delete(fromDB);
  }

}
