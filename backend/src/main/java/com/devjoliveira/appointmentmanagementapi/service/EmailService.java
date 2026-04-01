package com.devjoliveira.appointmentmanagementapi.service;

import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;

@Service
public class EmailService {

  public Message send(String to, String message) {
    System.out.println("Sending Email...");
    // JavaMailSender aqui
    return null;
  }

}
