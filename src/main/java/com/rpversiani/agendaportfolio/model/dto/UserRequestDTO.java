package com.rpversiani.agendaportfolio.model.dto;

import com.rpversiani.agendaportfolio.model.Enum.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO{
    private String name;
    private String username;
    private String email;
    private UserRole role;
}
