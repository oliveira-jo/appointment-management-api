package com.devjoliveira.appointmentmanagementapi.util;

import com.google.i18n.phonenumbers.*;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class PhoneUtils {

  private static final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

  public static String formatToE164(String rawPhone) {

    try {
      PhoneNumber number = phoneUtil.parse(rawPhone, "BR");

      if (!phoneUtil.isValidNumber(number)) {
        throw new IllegalArgumentException("Invalid phone number");
      }

      return phoneUtil.format(number, PhoneNumberUtil.PhoneNumberFormat.E164);

    } catch (NumberParseException e) {
      throw new IllegalArgumentException("Invalid phone format", e);
    }
  }

}