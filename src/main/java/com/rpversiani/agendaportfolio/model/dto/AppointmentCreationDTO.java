package com.rpversiani.agendaportfolio.model.dto;

import com.rpversiani.agendaportfolio.model.Enum.AppointmentStatus;
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
public class AppointmentCreationDTO {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private AppointmentStatus status;
    private UUID customerId;
    private UUID serviceTypeId;
    private UUID createdByUserId;
    private Boolean createdByAI;
}
