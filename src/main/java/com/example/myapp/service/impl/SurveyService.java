package com.example.myapp.service.impl;

import com.example.myapp.model.Question;
import com.example.myapp.model.Survey;

import java.util.List;

public interface SurveyService {
    List<Survey> getAllSurveys();

    Survey getServey(String surveyId);

    List<Question> getAllQuestionsOfSurvey(String surveyId);

    Question getQuestionOfSurvey(String surveyId, String questionId);

    String addNewSurveyQuestion(String surveyId, Question question);

    void deleteQuestionOfSurvey(String surveyId, String questionId);

}

