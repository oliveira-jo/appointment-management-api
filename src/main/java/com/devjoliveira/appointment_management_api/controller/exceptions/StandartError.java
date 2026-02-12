package com.devjoliveira.appointment_management_api.controller.exceptions;

import java.time.Instant;

public record StandartError(

    Instant timestamp,
    Integer status,
    String error,
    String message,
    String path

) {

}