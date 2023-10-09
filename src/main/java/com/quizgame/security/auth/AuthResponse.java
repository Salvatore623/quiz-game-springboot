package com.quizgame.security.auth;

import lombok.Builder;

@Builder
public record AuthResponse(String token) {
}
