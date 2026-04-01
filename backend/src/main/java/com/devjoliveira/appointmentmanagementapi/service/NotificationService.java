package com.devjoliveira.appointmentmanagementapi.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devjoliveira.appointmentmanagementapi.domain.Appointment;
import com.devjoliveira.appointmentmanagementapi.repository.AppointmentRepository;
import com.devjoliveira.appointmentmanagementapi.util.PhoneUtils;

@Service
public class NotificationService {

  @Value("${env.TWILIO_TEMPLATE_SID:}")
  private String TEMPLATE_SID;

  private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

  private final AppointmentRepository appointmentRepository;
  private final WhatsAppService whatsAppService;
  private final SmsService smsService;
  private final EmailService emailService;

  public NotificationService(AppointmentRepository appointmentRepository, WhatsAppService whatsAppService,
      SmsService smsService, EmailService emailService) {
    this.appointmentRepository = appointmentRepository;
    this.whatsAppService = whatsAppService;
    this.smsService = smsService;
    this.emailService = emailService;
  }

  public void sendReminderOneDayBefore() {

    LocalDate tomorrow = LocalDate.now().plusDays(1);
    LocalDateTime start = tomorrow.atStartOfDay();
    LocalDateTime end = tomorrow.atTime(23, 59, 59);
    List<Appointment> tomorrowAppointments = appointmentRepository.findByScheduledAtBetweenAndReminderSentFalse(start,
        end);

    for (Appointment appointment : tomorrowAppointments) {

      try {

        // Format phone number to E.164 format
        String formattedPhone = PhoneUtils.formatToE164(appointment.getCustomer().getPhone());

        String variables = String.format("""
            {
              "1": "%s",
              "2": "%s",
              "3": "%s",
            }
            """,
            appointment.getCustomer().getName(),
            appointment.getScheduledAt().toLocalDate(),
            appointment.getScheduledAt().toLocalTime());

        try {
          whatsAppService.send(formattedPhone, TEMPLATE_SID, variables);
          return;
        } catch (Exception e) {
          log.warn("WhatsApp failed, trying SMS", e);
        }

        // Fallback SMS
        try {
          smsService.send(formattedPhone, "Seu agendamento é amanhã às " +
              appointment.getScheduledAt().toLocalTime());
          return;
        } catch (Exception e) {
          log.warn("SMS failed, trying email", e);
        }

        // Fallback para email
        // emailService.send(appointment.getCustomer().getEmail(), "Seu agendamento é
        // amanhã às " +
        // appointment.getScheduledAt().toLocalTime());

        markAsSent(appointment);

      } catch (Exception e) {
        log.warn("Error sending message: {}", e.getMessage());

      }

    }

  }

  @Transactional
  public void markAsSent(Appointment appointment) {
    appointment.setReminderSent(true);
    appointmentRepository.save(appointment);
  }

}

// TEMPLATE:
// Nome: appointment_reminder

// Conteúdo:
// Olá {{1}}, lembramos que você possui um agendamento em {{2}} às {{3}}.

// Responda para reagendar se necessário.
