package com.rpversiani.agendaportfolio.utils;

import com.rpversiani.agendaportfolio.model.dto.*;
import com.rpversiani.agendaportfolio.model.entity.Appointment;
import com.rpversiani.agendaportfolio.model.entity.ServiceType;
import com.rpversiani.agendaportfolio.model.entity.User;

public class EntityToDTO {
    public static AppointmentResponseDTO appointmentEntityToResponseDTO(Appointment appointment) {
        return new AppointmentResponseDTO(
                appointment.getStartTime(),
                appointment.getEndTime(),
                appointment.getStatus(),
                appointment.getServiceType().getId(),
                appointment.getCreatedByUserId() != null ? appointment.getCreatedByUserId().getId() : null,
                appointment.isCreatedByAI()
        );
    }

    public static AppointmentCreationDTO appointmentEntityToCreationDTO(Appointment appointment) {
        return new AppointmentCreationDTO(
                appointment.getStartTime(),
                appointment.getEndTime(),
                appointment.getStatus(),
                appointment.getCustomer().getId(),
                appointment.getServiceType().getId(),
                appointment.getCreatedByUserId() != null ? appointment.getCreatedByUserId().getId() : null,
                appointment.isCreatedByAI()
        );
    }

    public static ServiceTypeDTO serviceTypeToDTO(ServiceType serviceType){
        return new ServiceTypeDTO(
                serviceType.getName(),
                serviceType.getDescription(),
                serviceType.getPrice(),
                serviceType.getDuration()
        );
    }

    public static UserRequestDTO userRequestToDTO(User user){
        return new UserRequestDTO(
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
    }

    public static UserResponseDTO userResponseToDTO(User user){
        return new UserResponseDTO(
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
    }
}
