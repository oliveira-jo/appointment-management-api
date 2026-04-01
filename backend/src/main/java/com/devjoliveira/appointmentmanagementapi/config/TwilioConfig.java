package com.devjoliveira.appointmentmanagementapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;

import jakarta.annotation.PostConstruct;

@Configuration
public class TwilioConfig {

  @Value("${env.TWILIO_ACCOUNT_SID:}")
  private String accountSid;

  @Value("${env.TWILIO_AUTH_TOKEN:}")
  private String authToken;

  @PostConstruct
  public void init() {
    Twilio.init(accountSid, authToken);
  }
}