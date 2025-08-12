package com.example.myapp.service.impl;

import com.example.myapp.model.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SurveyServiceImplTest {

    @Autowired
    private SurveyService surveyService;

    @Test
    void getAllQuestionsOfSurveyReturnsEmptyListForUnknownSurvey() {
        List<Question> questions = surveyService.getAllQuestionsOfSurvey("Unknown");
        assertNotNull(questions);
        assertTrue(questions.isEmpty());
    }
}
