package com.rpversiani.agendaportfolio.service;

import com.rpversiani.agendaportfolio.exception.custom.ResourceNotFoundException;
import com.rpversiani.agendaportfolio.model.dto.UserRequestDTO;
import com.rpversiani.agendaportfolio.model.dto.UserResponseDTO;
import com.rpversiani.agendaportfolio.model.entity.User;
import com.rpversiani.agendaportfolio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.rpversiani.agendaportfolio.utils.EntityToDTO.userResponseToDTO;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final PasswordService passwordService;

    public UserService(UserRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    public User getUserById(UUID id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public UserResponseDTO getUserResponseDTOById(UUID id) {
        return userResponseToDTO(getUserById(id));
    }

    public UserResponseDTO createUser(User newUser) {
        User user = new User();
        user.setName(newUser.getName());
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setRole(newUser.getRole());

        passwordService.checkPasswordStrength(newUser.getPassword());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));

        userRepository.save(user);
        return userResponseToDTO(user);
    }

    public void updateUser(UUID id, UserRequestDTO user) {
        User dbUser = getUserById(id);
        dbUser.setName(user.getName());
        dbUser.setUsername(user.getUsername());
        dbUser.setEmail(user.getEmail());
        dbUser.setRole(user.getRole());
        userRepository.save(dbUser);
    }

    public void changePassword(UUID id, String newPassword) {
        User user = getUserById(id);

        passwordService.validatePassword(user.getPassword(), newPassword);

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        User dbUser = getUserById(id);
        userRepository.delete(dbUser);
    }
}
