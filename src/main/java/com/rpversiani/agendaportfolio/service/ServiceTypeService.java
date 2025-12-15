package com.rpversiani.agendaportfolio.service;

import com.rpversiani.agendaportfolio.model.dto.ServiceTypeDTO;
import com.rpversiani.agendaportfolio.model.entity.ServiceType;
import com.rpversiani.agendaportfolio.repository.ServiceTypeRepository;
import com.rpversiani.agendaportfolio.utils.EntityToDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTypeService {

    private final ServiceTypeRepository serviceTypeRepository;

    public ServiceTypeService(ServiceTypeRepository serviceTypeRepository) {
        this.serviceTypeRepository = serviceTypeRepository;
    }

    public List<ServiceTypeDTO> getAllServiceTypes(){
        List<ServiceType> serviceType = serviceTypeRepository.findAll();
        return serviceType.stream().map(EntityToDTO::serviceTypeToDTO).toList();
    }
}
