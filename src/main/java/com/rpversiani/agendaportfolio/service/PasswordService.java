package com.rpversiani.agendaportfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public final Integer PASSWORD_MIN_LENGTH = 8;

    public void validatePassword(String userCurrentPassword, String newPassword) {
        if (passwordEncoder.matches(newPassword, userCurrentPassword)) {
            throw new IllegalArgumentException("A nova senha deve ser diferente da senha atual.");
        }

        if (newPassword.length() < PASSWORD_MIN_LENGTH) {
            throw new IllegalArgumentException("A nova senha deve ter no mínimo 8 caracteres.");
        }

        checkPasswordStrength(newPassword);
    }

    private void checkPasswordStrength(String newPassword){
        boolean isWeak = newPassword.length() < 8
                || !newPassword.matches(".*\\d.*")
                || !newPassword.matches(".*[A-Z].*");

        if (isWeak) {
            throw new IllegalArgumentException("A nova senha deve conter ao menos 8 caracteres, incluindo número e letra maiúscula.");
        }

    }

}
