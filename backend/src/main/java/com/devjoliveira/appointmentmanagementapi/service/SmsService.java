package com.devjoliveira.appointmentmanagementapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService {

  @Value("${env.TWILIO_SMS_NUMBER:}")
  private String from;

  public Message send(String to, String message) {
    return Message.creator(
        new PhoneNumber(to),
        new PhoneNumber(from),
        message).create();
  }

}