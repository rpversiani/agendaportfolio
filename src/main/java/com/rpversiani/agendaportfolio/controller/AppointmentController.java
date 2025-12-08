package com.rpversiani.agendaportfolio.controller;


import com.rpversiani.agendaportfolio.model.Entity.Appointment;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/appointment")
public class AppointmentController {

    //private final AppointmentService appointmentService;

    @GetMapping("/{id}")
    public ResponseEntity <List<Appointment>> getAppointmentsByUserId(@PathVariable UUID id) {
//        List<Appointment> appointment = appointmentService.getAppointmentsByCustomerId(id);
//        return new ResponseEntity<>(appointment, HttpStatus.OK);
        return null;
    }
}
