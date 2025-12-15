package com.rpversiani.agendaportfolio.controller;

import com.rpversiani.agendaportfolio.model.dto.ChangePasswordDTO;
import com.rpversiani.agendaportfolio.model.dto.UserRequestDTO;
import com.rpversiani.agendaportfolio.model.dto.UserResponseDTO;
import com.rpversiani.agendaportfolio.model.entity.User;
import com.rpversiani.agendaportfolio.service.PasswordService;
import com.rpversiani.agendaportfolio.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final PasswordService passwordService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable UUID id) {
        UserResponseDTO userDTO = userService.getUserResponseDTOById(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid User newUser) {
        UserResponseDTO userDTO = userService.createUser(newUser);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable UUID id, @RequestBody @Valid UserRequestDTO user) {
        userService.updateUser(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<Void> changePassword(@PathVariable UUID id, @PathVariable String newPassword) {
        userService.changePassword(id, newPassword);
        return ResponseEntity.noContent().build();
    }

}
