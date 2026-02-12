package com.devjoliveira.appointment_management_api.controller.exceptions;

import java.time.Instant;
import java.util.List;

public record ValidationError(
        Instant timestamp,
        Integer status,
        String error,
        String message,
        String path,
        List<FieldMessage> errors) {

}
