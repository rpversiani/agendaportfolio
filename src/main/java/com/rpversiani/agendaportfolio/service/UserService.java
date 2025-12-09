package com.rpversiani.agendaportfolio.service;

import com.rpversiani.agendaportfolio.model.entity.User;
import com.rpversiani.agendaportfolio.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public User createUser(User newUser) {
        User user = new User();
        user.setName(newUser.getName());
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());
        user.setEmail(newUser.getEmail());
        user.setRole(newUser.getRole());
        userRepository.save(user);
        return user;
    }

    public void updateUser(UUID id, User user) {
        User dbUser = getUserById(id);
        dbUser.setName(user.getName());
        dbUser.setUsername(user.getUsername());
        dbUser.setEmail(user.getEmail());
        dbUser.setPassword(user.getPassword());
        dbUser.setRole(user.getRole());
        userRepository.save(dbUser);
    }

    public void deleteUser(UUID id) {
        User dbUser = getUserById(id);
        userRepository.delete(dbUser);
    }
}
