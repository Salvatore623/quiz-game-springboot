package com.quizgame.security.auth;

import com.quizgame.security.config.JwtService;
import com.quizgame.security.user.Role;
import com.quizgame.security.user.User;
import com.quizgame.security.user.UserRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register( AuthRequest req){
        userRepository.findByUsername(req.username())
                .ifPresent(user -> {
                    throw new EntityExistsException(user.getUsername() + " è già in uso");
                });

        var user = User.builder()
                .username(req.username())
                .password(passwordEncoder.encode(req.password()))
                .bestScoreEasy(0)
                .bestScoreMedium(0)
                .bestScoreHard(0)
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                ));
        var user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username non trovata"));

        String token = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(token)
                .build();
    }
}
