package com.rpversiani.agendaportfolio.service;

import com.rpversiani.agendaportfolio.model.entity.Appointment;
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
        return appointmentRepository.findAllByCustomerId(id);
    }

    public Appointment createAppointment(Appointment newAppointment){
        //TODO Criar a validação do agendamento e todo o processamento da regra de negócio

        Appointment appointment = new Appointment();

        appointment.setStartTime(newAppointment.getStartTime());
        appointment.setEndTime(newAppointment.getEndTime());
        appointment.setStatus((newAppointment.getStatus()));
        appointment.setServiceType(newAppointment.getServiceType());
        appointment.setCustomer(newAppointment.getCustomer());
        appointment.setCreatedByUserId(newAppointment.getCreatedByUserId());
        appointment.setCreatedByAI(newAppointment.isCreatedByAI());

        appointmentRepository.save(appointment);
        return appointment;
    }

    //TODO criar os métodos de update e delete
}
