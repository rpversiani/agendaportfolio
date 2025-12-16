package com.rpversiani.agendaportfolio.exception;

import java.time.Instant;

public record ApiError(
        String code,
        String error,
        int status,
        String path,
        Instant timestamp
){}

