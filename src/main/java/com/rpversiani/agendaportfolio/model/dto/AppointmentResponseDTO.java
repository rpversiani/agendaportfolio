package com.rpversiani.agendaportfolio.model.dto;

import com.rpversiani.agendaportfolio.model.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponseDTO {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private AppointmentStatus status;
    private UUID serviceType;
    private UUID createdByUserId;
    private Boolean createdByAI;
}
