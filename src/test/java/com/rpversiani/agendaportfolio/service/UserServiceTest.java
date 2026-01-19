package com.rpversiani.agendaportfolio.service;

import com.rpversiani.agendaportfolio.exception.custom.PasswordException;
import com.rpversiani.agendaportfolio.exception.custom.ResourceNotFoundException;
import com.rpversiani.agendaportfolio.model.dto.UserRequestDTO;
import com.rpversiani.agendaportfolio.model.dto.UserResponseDTO;
import com.rpversiani.agendaportfolio.model.entity.User;
import com.rpversiani.agendaportfolio.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static com.rpversiani.agendaportfolio.utils.TestData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository repository;

    @Mock
    private PasswordService passwordService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService service;

    @Test
    void givenExistingUser_whenGetUserById_thenReturnUser() {
        User user = generateValidUser();

        when(repository.findById(user.getId())).thenReturn(Optional.of(user));

        User result = service.getUserById(user.getId());

        assertNotNull(result);
        assertEquals(user, result);
        verify(repository).findById(user.getId());
    }

    @Test
    void givenNonExistingUserId_whenGetUserById_thenThrowException() {
        UUID id = generateValidUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> service.getUserById(id)
        );

        verify(repository).findById(id);
    }

    @Test
    void givenExistingUserId_whenGetUserResponseDTOById_thenReturnMappedUserResponseDTO(){
        User user = generateValidUser();
        UserResponseDTO dto = generateValidUserResponseDTO();

        when(repository.findById(user.getId())).thenReturn(Optional.of(user));

        UserResponseDTO result = service.getUserResponseDTOById(user.getId());

        assertNotNull(result);
        assertEquals(dto, result);
        verify(repository).findById(user.getId());
    }

    @Test
    void givenNonExistingUserId_whenGetUserResponseDTOById_thenThrowException() {
        UUID id = generateValidUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> service.getUserResponseDTOById(id)
        );

        verify(repository).findById(id);
    }

    @Test
    void givenValidUser_whenCreateUser_thenSaveUserAndReturnUserResponseDTO(){
        User user = generateValidUser();

        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");

        UserResponseDTO result = service.createUser(user);

        assertNotNull(result);
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getRole(), result.getRole());

        verify(passwordService).checkPasswordStrength(user.getPassword());
        verify(passwordEncoder).encode(user.getPassword());
        verify(repository).save(any(User.class));
    }

    @Test
    void givenWeakPassword_whenCreateUser_thenThrowPasswordException() {
        User user = generateValidUser();
        user.setPassword("weak");

        doThrow(PasswordException.class)
                .when(passwordService)
                .checkPasswordStrength(user.getPassword());

        assertThrows(
                PasswordException.class,
                () -> service.createUser(user)
        );

        verify(passwordService).checkPasswordStrength(user.getPassword());
        verify(passwordEncoder, never()).encode(any());
        verify(repository, never()).save(any());
    }

    @Test
    void givenExistingUser_whenUpdateUser_thenUpdateFieldsAndSave() {
        User dbUser = generateValidUser();

        UserRequestDTO request = generateValidUserRequestDTO();

        when(repository.findById(dbUser.getId())).thenReturn(Optional.of(dbUser));

        assertDoesNotThrow(() -> service.updateUser(dbUser.getId(), request));

        assertEquals(request.getName(), dbUser.getName());
        assertEquals(request.getUsername(), dbUser.getUsername());
        assertEquals(request.getEmail(), dbUser.getEmail());
        assertEquals(request.getRole(), dbUser.getRole());

        verify(repository).save(dbUser);
    }

    @Test
    void givenNonExistingUserId_whenUpdateUser_thenThrowException() {
        UUID id = generateValidUUID();
        UserRequestDTO request = generateValidUserRequestDTO();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> service.updateUser(id, request)
        );

        verify(repository, never()).save(any());
    }

    @Test
    void givenValidPassword_whenChangePassword_thenEncodeAndSaveNewPassword() {
        User user = generateValidUser();
        user.setPassword("weak");

        String newPassword = "NewStrong123";

        when(repository.findById(user.getId())).thenReturn(Optional.of(user));
        when(passwordEncoder.encode(newPassword)).thenReturn("encodedNewPassword");

        assertDoesNotThrow(() -> service.changePassword(user.getId(), newPassword));

        verify(passwordService).validatePassword("weak", newPassword);
        verify(passwordEncoder).encode(newPassword);
        verify(repository).save(user);

        assertEquals("encodedNewPassword", user.getPassword());
    }

    @Test
    void givenInvalidPassword_whenChangePassword_thenThrowExceptionAndNotSave() {
        User user = generateValidUser();

        String weakPassword = "weak";

        when(repository.findById(user.getId())).thenReturn(Optional.of(user));

        doThrow(PasswordException.class)
                .when(passwordService)
                .validatePassword(user.getPassword(), weakPassword);

        assertThrows(
                PasswordException.class,
                () -> service.changePassword(user.getId(), weakPassword)
        );

        verify(passwordService).validatePassword(user.getPassword(), weakPassword);
        verify(passwordEncoder, never()).encode(any());
        verify(repository, never()).save(any());
    }

    @Test
    void givenExistingUserId_whenDeleteUser_thenDeleteUser() {
        User user = generateValidUser();

        when(repository.findById(user.getId())).thenReturn(Optional.of(user));

        assertDoesNotThrow(() -> service.deleteUser(user.getId()));

        verify(repository).delete(user);
    }

    @Test
    void givenNonExistingUserId_whenDeleteUser_thenThrowException() {
        UUID id = generateValidUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> service.deleteUser(id)
        );

        verify(repository, never()).delete(any());
    }

}
