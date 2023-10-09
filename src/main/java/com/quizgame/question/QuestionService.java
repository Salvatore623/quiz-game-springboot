package com.quizgame.question;

import com.quizgame.answer.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<QuestionDto> getLevelQuestions(LevelQuestion level, int limit) {
        List<Question> questions = questionRepository.findByLevel(level, PageRequest.of(0, Integer.MAX_VALUE));

        Collections.shuffle(questions);

        List<Question> randomQuestions = questions.stream()
                .limit(limit)
                .toList();

        return randomQuestions.stream()
                .map(question -> {
                    Answer answer = question.getAnswers();

                    List<String> allAnswers = Stream.concat(
                                    Stream.of(answer.getCorrectAnswer()),
                                    Arrays.stream(answer.getErrorAnswers())
                            )
                            .collect(Collectors.toList());

                    Collections.shuffle(allAnswers);

                    return QuestionDto.builder()
                            .id(question.getId())
                            .question(question.getQuestion())
                            .answers(allAnswers)
                            .build();
                })
                .collect(Collectors.toList());
    }
}
