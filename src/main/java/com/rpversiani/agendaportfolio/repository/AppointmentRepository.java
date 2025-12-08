package com.rpversiani.agendaportfolio.repository;

import com.rpversiani.agendaportfolio.model.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
//    List<Appointment> findAllByClient(UUID id);
}
