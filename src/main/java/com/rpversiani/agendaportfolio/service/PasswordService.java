package com.rpversiani.agendaportfolio.service;

import com.rpversiani.agendaportfolio.exception.custom.PasswordException;
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
            throw new PasswordException("The new password must be different from the current password");
        }

        checkPasswordStrength(newPassword);
    }

    public void checkPasswordStrength(String newPassword){
        boolean isWeak = newPassword.length() < PASSWORD_MIN_LENGTH
                || !newPassword.matches(".*\\d.*")
                || !newPassword.matches(".*[A-Z].*");

        if (isWeak) {
            throw new PasswordException("The new password must contain at least 8 characters, " +
                    "including numbers and uppercase letters");
        }
    }
}
