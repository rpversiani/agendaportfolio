package com.rpversiani.agendaportfolio.controller;


import com.rpversiani.agendaportfolio.model.dto.AppointmentCreationDTO;
import com.rpversiani.agendaportfolio.model.dto.AppointmentResponseDTO;
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
    public ResponseEntity <List<AppointmentResponseDTO>> getAppointmentsByCustomerId(@PathVariable UUID id) {
        List<AppointmentResponseDTO> appointment = appointmentService.getAppointmentsByCustomerId(id);
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<AppointmentCreationDTO> createUser(@RequestBody @Valid AppointmentCreationDTO newAppointment) {
        AppointmentCreationDTO appointment = appointmentService.createAppointment(newAppointment);
        return new ResponseEntity<>(appointment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        appointmentService.deleteAppointment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
