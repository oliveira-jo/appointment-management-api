package com.devjoliveira.appointmentmanagementapi.jobs;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.devjoliveira.appointmentmanagementapi.service.AppointmentService;

@Component
public class AppointmentScheduler {

  private final AppointmentService service;

  public AppointmentScheduler(AppointmentService service) {
    this.service = service;

  }

  @Scheduled(fixedRate = 60000) // a cada 1 minuto
  public void run() {
    System.out.println("Scheduler rodando...");
    service.updateCompletedAppointments();
  }
}