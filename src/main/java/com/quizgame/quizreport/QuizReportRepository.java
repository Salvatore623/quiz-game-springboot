package com.quizgame.quizreport;

import com.quizgame.question.LevelQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizReportRepository extends JpaRepository<QuizReport, Integer> {

    List<QuizReport> findFirst10ByLevelOrderByScoreDesc(LevelQuestion level);

}
