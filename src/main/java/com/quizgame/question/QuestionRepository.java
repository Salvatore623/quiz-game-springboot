package com.quizgame.question;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByLevel(LevelQuestion level, Pageable pageable);

    Integer countByLevel(LevelQuestion level);
}
