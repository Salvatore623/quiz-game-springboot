package com.quizgame.quizreport;

import com.quizgame.answer.AnswerRepository;
import com.quizgame.question.LevelQuestion;
import com.quizgame.security.user.User;
import com.quizgame.security.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizReportService {

    private final QuizReportRepository quizReportRepository;
    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;
    Integer count = 0;
    List<QuizReportCheckAnswerResponseDto> quizReportCheckAnswerRes = new ArrayList<>();

    public QuizReportResponse createReportQuiz(QuizReportCreateDto quizReport) {
        quizReportCheckAnswerRes.clear();
        var user = userRepository.findByUsername(quizReport.username())
                .orElseThrow(() -> new EntityNotFoundException("Username non esistente"));

        quizReport.answers().forEach(obj -> {
            var question = answerRepository.findByQuestionId(obj.questionId())
                    .orElseThrow(() -> new EntityNotFoundException("Domanda non trovata"));
            quizReportCheckAnswer(question.getQuestion().getQuestion(), obj.answer(), question.getCorrectAnswer());
            if (obj.answer().equals(question.getCorrectAnswer())) {
                count++;
            }
        });

        var score = (count * 50) + quizReport.timeLeft();
        updateRecordScore(quizReport.level(), user, score);
        quizReportRepository.save(createQuizReport(quizReport,user, score, count));
        var quizResponse = QuizReportResponse.builder()
                .correctUserAnswersCount(count)
                .score(score)
                .quizReportCheckAnswer(quizReportCheckAnswerRes)
                .build();

        count = 0;

        return quizResponse;

    }

    private void quizReportCheckAnswer(String question, String userAnswer, String correctAnswer){
        var obj = QuizReportCheckAnswerResponseDto.builder()
                .question(question)
                .userAnswer(userAnswer)
                .correctAnswer(correctAnswer)
                .build();
        quizReportCheckAnswerRes.add(obj);
    }

    private QuizReport createQuizReport(QuizReportCreateDto quizReport, User user, Integer score, Integer correctAnswerCount){
        return QuizReport.builder()
                .username(user.getUsername())
                .timeLeft(quizReport.timeLeft())
                .level(quizReport.level())
                .correctAnswers(correctAnswerCount)
                .score(score)
                .build();
    }
    private void updateRecordScore(LevelQuestion level, User user, Integer score){
        switch (level){
            case EASY -> updateScoreEasy(user, score);
            case MEDIUM -> updateScoreMedium(user, score);
            case HARD -> updateScoreHard(user, score);
        };
    }
    private void updateScoreEasy(User user, Integer score){
        if(user.getBestScoreEasy() < score){
            user.setBestScoreEasy(score);
            userRepository.save(user);
        }
    }
    private void updateScoreMedium(User user, Integer score){
        if(user.getBestScoreMedium() < score){
            user.setBestScoreMedium(score);
            userRepository.save(user);
        }
    }

    private void updateScoreHard(User user, Integer score){
        if(user.getBestScoreHard() < score){
            user.setBestScoreHard(score);
            userRepository.save(user);
        }
    }




}
