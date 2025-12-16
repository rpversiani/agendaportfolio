package com.rpversiani.agendaportfolio.exception.custom;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BusinessException {
    public ResourceNotFoundException(String message) {
        super(
                "RESOURCE_NOT_FOUND",
                message,
                HttpStatus.NOT_FOUND
        );
    }
}

