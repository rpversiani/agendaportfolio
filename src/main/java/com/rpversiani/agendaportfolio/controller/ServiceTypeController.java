package com.rpversiani.agendaportfolio.controller;

import com.rpversiani.agendaportfolio.model.dto.ServiceTypeDTO;
import com.rpversiani.agendaportfolio.model.entity.ServiceType;
import com.rpversiani.agendaportfolio.service.ServiceTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/service-type")
public class ServiceTypeController {

    private final ServiceTypeService serviceTypeService;

    @GetMapping("/{id}")
    public ResponseEntity<List<ServiceTypeDTO>> getServiceTypes() {
        List<ServiceTypeDTO> serviceTypes = serviceTypeService.getAllServiceTypes();
        return new ResponseEntity<>(serviceTypes, HttpStatus.OK);
    }
}
