package com.rpversiani.agendaportfolio.repository;

import com.rpversiani.agendaportfolio.model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
