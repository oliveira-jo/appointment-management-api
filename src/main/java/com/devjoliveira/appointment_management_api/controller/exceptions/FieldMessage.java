package com.devjoliveira.appointment_management_api.controller.exceptions;

public record FieldMessage(
    String fieldName, String message) {
}
