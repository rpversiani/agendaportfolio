package com.rpversiani.agendaportfolio.utils;

import com.rpversiani.agendaportfolio.model.enums.UserRole;
import com.rpversiani.agendaportfolio.model.dto.UserRequestDTO;
import com.rpversiani.agendaportfolio.model.dto.UserResponseDTO;
import com.rpversiani.agendaportfolio.model.entity.User;

import java.util.UUID;

public class TestData {

    public static UUID generateValidUUID(){
        return UUID.randomUUID();
    }

    public static User generateValidUser(){
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName("name");
        user.setUsername("username");
        user.setPassword("Password123");
        user.setEmail("user@email.com.br");
        user.setRole(UserRole.DEFAULT);
        return user;
    }

    public static UserResponseDTO generateValidUserResponseDTO(){
        UserResponseDTO user = new UserResponseDTO();
        user.setName("name");
        user.setUsername("username");
        user.setEmail("user@email.com.br");
        user.setRole(UserRole.DEFAULT);
        return user;
    }

    public static UserRequestDTO generateValidUserRequestDTO(){
        UserRequestDTO user = new UserRequestDTO();
        user.setName("name");
        user.setUsername("username");
        user.setEmail("user@email.com.br");
        user.setRole(UserRole.DEFAULT);
        return user;
    }
}
