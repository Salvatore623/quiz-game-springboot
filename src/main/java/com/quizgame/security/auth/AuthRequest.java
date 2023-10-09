package com.quizgame.security.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequest(
        @NotBlank
        @Size(min = 1, max = 16)
        String username,
        @NotBlank
        @Size(min = 8, max = 26, message = "Password minimo 8 caratteri")
        String password) {
}
