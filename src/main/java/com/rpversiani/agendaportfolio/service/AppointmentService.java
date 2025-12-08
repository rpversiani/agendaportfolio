package com.rpversiani.agendaportfolio.service;

import com.rpversiani.agendaportfolio.model.Entity.Appointment;
import com.rpversiani.agendaportfolio.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAppointmentsByCustomerId(UUID id){
//        return appointmentRepository.findAllByClient(id);
        return null;
    }
}
