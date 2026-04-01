package com.devjoliveira.appointmentmanagementapi.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  private final JavaMailSender mailSender;

  public EmailService(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public void send(String to, String subject, String message) {
    try {
      SimpleMailMessage mail = new SimpleMailMessage();
      mail.setTo(to);
      mail.setSubject(subject);
      mail.setText(message);

      mailSender.send(mail);

    } catch (Exception e) {
      throw new RuntimeException("Failed to send email", e);
    }
  }
}
