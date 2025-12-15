package com.rpversiani.agendaportfolio.service;

import com.rpversiani.agendaportfolio.model.dto.AppointmentCreationDTO;
import com.rpversiani.agendaportfolio.model.dto.AppointmentResponseDTO;
import com.rpversiani.agendaportfolio.model.entity.Appointment;
import com.rpversiani.agendaportfolio.model.entity.ServiceType;
import com.rpversiani.agendaportfolio.model.entity.User;
import com.rpversiani.agendaportfolio.repository.AppointmentRepository;
import com.rpversiani.agendaportfolio.repository.ServiceTypeRepository;
import com.rpversiani.agendaportfolio.repository.UserRepository;
import com.rpversiani.agendaportfolio.utils.EntityToDTO;
import jakarta.persistence.EntityNotFoundException;
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

    Appointment appointment = new Appointment();

    public AppointmentService(AppointmentRepository appointmentRepository, UserRepository userRepository, ServiceTypeRepository serviceTypeRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        this.serviceTypeRepository = serviceTypeRepository;
    }

    public Appointment getAppointmentById(UUID id){
        return appointmentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<AppointmentResponseDTO> getAppointmentsByCustomerId(UUID id) {
        List<Appointment> appointment = appointmentRepository.findAllByCustomerId(id);

        return appointment.stream()
                .map(EntityToDTO::appointmentEntityToResponseDTO)
                .toList();
    }

    public AppointmentCreationDTO createAppointment(AppointmentCreationDTO dto) {
        //TODO Criar a validação do agendamento e todo o processamento da regra de negócio

        validateCreatedByField(dto);

        checkServiceSchedulingConflicts(dto.getCustomerId(),dto.getStartTime(),dto.getEndTime());

        appointment.setStartTime(dto.getStartTime());
        appointment.setEndTime(dto.getEndTime());
        appointment.setStatus((dto.getStatus()));
        appointment.setCustomer(getCustomer(dto));
        appointment.setServiceType(getServiceType(dto));
        appointment.setCreatedByAI(dto.getCreatedByAI());

        appointmentRepository.save(appointment);
        return appointmentEntityToCreationDTO(appointment);
    }

    public void deleteAppointment(UUID id) {
        Appointment appointment = getAppointmentById(id);
        appointmentRepository.delete(appointment);
    }

    private void validateCreatedByField(AppointmentCreationDTO dto) {
        if (dto.getCreatedByUserId() != null) {
            User createdByUser = userRepository.findById(dto.getCreatedByUserId())
                    .orElseThrow(() -> new EntityNotFoundException("Creator user not found"));

            appointment.setCreatedByUserId(createdByUser);
        }
    }

    private ServiceType getServiceType(AppointmentCreationDTO dto){
        return serviceTypeRepository.findById(dto.getServiceTypeId())
                .orElseThrow(() -> new EntityNotFoundException("Service type not found"));
    }

    private User getCustomer(AppointmentCreationDTO dto){
        return userRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }

    private void checkServiceSchedulingConflicts(UUID customerId, LocalDateTime startTime, LocalDateTime endTime){

        List<Appointment> appointments = appointmentRepository.findAllByCustomerId(customerId);

        if(!appointments.isEmpty()){
            for (Appointment appointment : appointments) {
                if(appointment.getStartTime().equals(startTime)){
                    throw new RuntimeException("There is a scheduled service for the same start date.");
                }
                if(appointment.getEndTime().isAfter(startTime)){
                    throw new RuntimeException("Existe agendamento com o encerramento programado para depois do horário deste agendamento.");
                }
            }
        }


    }
}
