package com.rpversiani.agendaportfolio.model.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceTypeDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer duration;
}
