package com.rpversiani.agendaportfolio.repository;

import com.rpversiani.agendaportfolio.model.entity.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceTypeRepository extends JpaRepository<ServiceType, UUID> {
}
