package com.devjoliveira.appointment_management_api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devjoliveira.appointment_management_api.domain.Appointment;
import com.devjoliveira.appointment_management_api.domain.Customer;
import com.devjoliveira.appointment_management_api.domain.Product;
import com.devjoliveira.appointment_management_api.domain.Professional;
import com.devjoliveira.appointment_management_api.dto.AppointmentDTO;
import com.devjoliveira.appointment_management_api.enums.AppointmentStatus;
import com.devjoliveira.appointment_management_api.repository.AppointmentRepository;
import com.devjoliveira.appointment_management_api.repository.CustomerRepository;
import com.devjoliveira.appointment_management_api.repository.ProductRepository;
import com.devjoliveira.appointment_management_api.repository.ProfessionalRepository;
import com.devjoliveira.appointment_management_api.service.exceptions.DatabaseException;
import com.devjoliveira.appointment_management_api.service.exceptions.ResourceNotFoundException;

@Service
public class AppointmentService {

  private final AppointmentRepository appointmentRepository;
  private final ProductRepository productRepository;
  private final ProfessionalRepository professionalRepository;
  private final CustomerRepository customerRepository;

  public AppointmentService(AppointmentRepository appointmentRepository,
      ProductRepository productRepository,
      ProfessionalRepository professionalRepository,
      CustomerRepository customerRepository) {
    this.appointmentRepository = appointmentRepository;
    this.productRepository = productRepository;
    this.professionalRepository = professionalRepository;
    this.customerRepository = customerRepository;
  }

  @Transactional(readOnly = true)
  public List<AppointmentDTO> findAppointmentsForProfessional(UUID professionalId) {
    List<Appointment> appointments = appointmentRepository.findByProfessionalId(professionalId);
    return appointments.stream().map(AppointmentDTO::new).toList();
  }

  @Transactional(readOnly = true)
  public List<AppointmentDTO> findByProfessionalIdAndScheduledAtBetween(UUID professionalId,
      LocalDateTime start,
      LocalDateTime end) {
    List<Appointment> appointments = appointmentRepository.findByProfessionalIdAndScheduledAtBetween(professionalId,
        start, end);
    return appointments.stream().map(AppointmentDTO::new).toList();
  }

  @Transactional(readOnly = true)
  public Page<AppointmentDTO> findAllPaged(Pageable pageable) {

    Page<Appointment> appointments = appointmentRepository.findAll(pageable);
    return appointments.map(AppointmentDTO::new);
  }

  @Transactional
  public AppointmentDTO createAppointment(String customerEmail, String professionalEmail, String productName,
      LocalDateTime scheduledAt) {

    Customer customer = customerRepository.findByEmail(customerEmail).orElseThrow(
        () -> new ResourceNotFoundException("Customer not found with email: " + customerEmail));
    Professional professional = professionalRepository.findByEmail(professionalEmail).orElseThrow(
        () -> new ResourceNotFoundException("Professional not found with email: " + professionalEmail));
    Product product = productRepository.findByName(productName).orElseThrow(
        () -> new ResourceNotFoundException("Product not found with name: " + productName));

    LocalDateTime endsAt = scheduledAt.plusSeconds(product.getDurationInSeconds().toSeconds());

    List<Appointment> conflicts = appointmentRepository.findConflictingAppointments(professional.getId(), scheduledAt,
        endsAt);

    if (!conflicts.isEmpty()) {
      throw new IllegalStateException(
          "Professional: " + professional.getName() + " is not available at the requested time: " + scheduledAt);
    }

    Appointment appointment = new Appointment();
    appointment.setCustomer(customer);
    appointment.setProfessional(professional);
    appointment.setProduct(product);
    appointment.setScheduledAt(scheduledAt);
    appointment.setEndsAt(endsAt);
    appointment.setStatus(AppointmentStatus.SCHEDULED);

    var fromDB = appointmentRepository.save(appointment);

    return new AppointmentDTO(fromDB);
  }

  @Transactional
  public void delete(UUID id) {

    var fromDB = appointmentRepository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException("Appointment with id " + id + " not found"));

    try {
      appointmentRepository.delete(fromDB);
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException("Fail in reference integrity");
    }

  }

}
