package com.quizgame.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quizgame.answer.Answer;
import com.quizgame.answer.AnswerController;
import com.quizgame.answer.AnswerCreateDto;
import com.quizgame.answer.AnswerService;
import com.quizgame.question.Question;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(AnswerController.class)
@Slf4j
@AutoConfigureMockMvc
public class AnswerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AnswerService answerService;


    @Test
    public void createAnswer() throws Exception {
        AnswerCreateDto answerCreateDto = new AnswerCreateDto("correct", new String[]{"error1", "error2", "error3"}, 1);
        Answer createdAnswer = new Answer(1, "correct", new String[]{"error1", "error2", "error3"}, new Question());
        Mockito.when(answerService.createAnswer(Mockito.any(AnswerCreateDto.class))).thenReturn(createdAnswer);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/answer/create")
                        .with(SecurityMockMvcRequestPostProcessors.jwt())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(answerCreateDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}