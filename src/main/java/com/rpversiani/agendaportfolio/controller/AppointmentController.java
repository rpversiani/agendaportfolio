package com.rpversiani.agendaportfolio.controller;


import com.rpversiani.agendaportfolio.model.entity.Appointment;
import com.rpversiani.agendaportfolio.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping("/{id}")
    public ResponseEntity <List<Appointment>> getAppointmentsByCustomerId(@PathVariable UUID id) {
        List<Appointment> appointment = appointmentService.getAppointmentsByCustomerId(id);
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Appointment> createUser(@RequestBody @Valid Appointment newAppointment) {
        Appointment appointment = appointmentService.createAppointment(newAppointment);
        return new ResponseEntity<>(appointment, HttpStatus.CREATED);
    }

    //TODO fazer a controller de update e delete
    //TODO criar os DTOs das entidades
}
