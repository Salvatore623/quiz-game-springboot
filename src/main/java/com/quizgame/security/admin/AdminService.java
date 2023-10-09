package com.quizgame.security.admin;

import com.quizgame.question.Question;
import com.quizgame.question.QuestionRepository;
import com.quizgame.security.user.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.quizgame.question.LevelQuestion.*;
import static com.quizgame.security.user.Role.ROLE_ADMIN;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public Question createQuestion(Question question){
        return questionRepository.save(question);
    }

    public Map<String, Integer> findCountQuestions(){
        Map<String, Integer> count = new HashMap<>();
        var easy = questionRepository.countByLevel(EASY);
        var medium = questionRepository.countByLevel(MEDIUM);
        var hard = questionRepository.countByLevel(HARD);
        count.put("easy", easy);
        count.put("medium", medium);
        count.put("hard", hard);
        return count;
    }

    public void changeRole(String username) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Username non trovata"));

        if(user.getRole().name().equals(ROLE_ADMIN.name())){
            throw new EntityExistsException("Il ruolo di " + username + " è già admin");
        }

        user.setRole(ROLE_ADMIN);
        userRepository.save(user);
    }
}
