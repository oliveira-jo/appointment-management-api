package com.devjoliveira.appointmentmanagementapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class WhatsAppService {

  @Value("${env.TWILIO_WHATSAPP_NUMBER:}")
  private String from;

  public Message sendTemplate(String to, String templateSid, String variablesJson) {

    return Message.creator(
        new PhoneNumber("whatsapp:" + to),
        new PhoneNumber(from),
        (String) null)
        .setContentSid(templateSid)
        .setContentVariables(variablesJson)
        .create();
  }

}