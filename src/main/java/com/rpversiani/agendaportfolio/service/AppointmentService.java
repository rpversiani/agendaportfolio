package com.rpversiani.agendaportfolio.service;

import com.rpversiani.agendaportfolio.exception.custom.AppointmentException;
import com.rpversiani.agendaportfolio.exception.custom.ResourceNotFoundException;
import com.rpversiani.agendaportfolio.model.dto.AppointmentCreationDTO;
import com.rpversiani.agendaportfolio.model.dto.AppointmentResponseDTO;
import com.rpversiani.agendaportfolio.model.entity.Appointment;
import com.rpversiani.agendaportfolio.model.entity.ServiceType;
import com.rpversiani.agendaportfolio.model.entity.User;
import com.rpversiani.agendaportfolio.repository.AppointmentRepository;
import com.rpversiani.agendaportfolio.repository.ServiceTypeRepository;
import com.rpversiani.agendaportfolio.repository.UserRepository;
import com.rpversiani.agendaportfolio.utils.EntityToDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.rpversiani.agendaportfolio.utils.EntityToDTO.appointmentEntityToCreationDTO;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final UserRepository userRepository;

    private final ServiceTypeRepository serviceTypeRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, UserRepository userRepository, ServiceTypeRepository serviceTypeRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        this.serviceTypeRepository = serviceTypeRepository;
    }

    public Appointment getAppointmentById(UUID id){
        return appointmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
    }

    public List<AppointmentResponseDTO> getAppointmentsByCustomerId(UUID id) {
        List<Appointment> appointment = appointmentRepository.findAllByCustomerId(id);

        return appointment.stream()
                .map(EntityToDTO::appointmentEntityToResponseDTO)
                .toList();
    }

    //TODO Revisar regras de agendamento
    public AppointmentCreationDTO createAppointment(AppointmentCreationDTO dto) {
        Appointment appointment = new Appointment();

        checkSchedulingConflicts(dto.getCustomerId(),dto.getStartTime());

        appointment.setStartTime(dto.getStartTime());
        appointment.setEndTime(dto.getEndTime());
        appointment.setStatus((dto.getStatus()));
        appointment.setCustomer(getCustomer(dto));
        appointment.setCreatedByUserId(getCreatedByUserId(dto));
        appointment.setServiceType(getServiceType(dto));
        appointment.setCreatedByAI(dto.getCreatedByAI());

        appointmentRepository.save(appointment);
        return appointmentEntityToCreationDTO(appointment);
    }

    public void deleteAppointment(UUID id) {
        Appointment appointment = getAppointmentById(id);
        appointmentRepository.delete(appointment);
    }

    private User getCreatedByUserId(AppointmentCreationDTO dto){
            return userRepository.findById(dto.getCreatedByUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("Creator user not found"));
    }

    private ServiceType getServiceType(AppointmentCreationDTO dto){
        return serviceTypeRepository.findById(dto.getServiceTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Service type not found"));
    }

    private User getCustomer(AppointmentCreationDTO dto){
        return userRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }

    private void checkSchedulingConflicts(UUID customerId, LocalDateTime startTime){

        List<Appointment> appointments = appointmentRepository.findAllByCustomerId(customerId);

        for (Appointment appointment : appointments) {
            if(appointment.getStartTime().equals(startTime) || appointment.getEndTime().isAfter(startTime)){
                throw new AppointmentException("There is a scheduling conflict. Try changing the date or time of the appointment.");
            }
        }
    }
}
