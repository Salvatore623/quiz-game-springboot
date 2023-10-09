package com.quizgame.profile;

import com.quizgame.security.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserRepository userRepository;

    public Profile getProfileByUsername(String username){
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Username non trovata"));

        return Profile.builder()
                .username(user.getUsername())
                .bestScoreEasy(user.getBestScoreEasy())
                .bestScoreMedium(user.getBestScoreMedium())
                .bestScoreHard(user.getBestScoreHard())
                .avatar(user.getAvatar())
                .build();
    }

    public void changeAvatar(ChangeAvatarRequest req) {
        var user = userRepository.findByUsername(req.username())
                .orElseThrow(() -> new EntityNotFoundException("Username non trovata"));

        user.setAvatar(req.avatar());
        userRepository.save(user);
    }
}
