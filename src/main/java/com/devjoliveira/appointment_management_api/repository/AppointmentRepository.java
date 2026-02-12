package com.devjoliveira.appointment_management_api.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devjoliveira.appointment_management_api.domain.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

  @Query("""
          SELECT a FROM Appointment a
          WHERE a.professional.id = :professionalId
          AND a.status <> 'CANCELED'
          AND a.scheduledAt < :end
          AND a.endsAt > :start
      """)
  List<Appointment> findConflictingAppointments(UUID professionalId, LocalDateTime start, LocalDateTime end);

}
