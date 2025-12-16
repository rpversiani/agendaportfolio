package com.rpversiani.agendaportfolio.exception.custom;

import org.springframework.http.HttpStatus;

public class AppointmentException extends BusinessException{
    public AppointmentException(String message) {
        super(
                "APPOINTMENT_TIME_CONFLICT",
                message,
                HttpStatus.CONFLICT
        );
    }
}
