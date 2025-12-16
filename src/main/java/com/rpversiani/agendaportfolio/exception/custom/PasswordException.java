package com.rpversiani.agendaportfolio.exception.custom;

import org.springframework.http.HttpStatus;

public class PasswordException extends BusinessException{
    public PasswordException(String message) {
        super(
                "INVALID_PASSWORD",
                message,
                HttpStatus.NOT_ACCEPTABLE
        );
    }
}
