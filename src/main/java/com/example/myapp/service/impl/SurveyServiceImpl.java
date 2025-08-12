package com.example.myapp.service.impl;

import com.example.myapp.model.Question;
import com.example.myapp.model.Survey;
import com.example.myapp.repository.InMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.myapp.service.utils.Constants.*;

@Service
public class SurveyServiceImpl implements SurveyService{

    @Autowired
    InMemoryRepository repository;

    @Override
    public List<Survey> getAllSurveys() {
        return repository.getSurveys();
    }

    @Override
    public Survey getServey(String surveyId) {

       List<Survey> surveys = getAllSurveys();
       Optional<Survey> optionalSurvey =  surveys.stream().filter(
                survey -> survey.getId().equals(surveyId)
        ).findFirst();

       return optionalSurvey.orElse(NOT_AVAILABLE_SURVEY);
    }

    @Override
    public List<Question> getAllQuestionsOfSurvey(String surveyId) {

        List<Survey> surveys = getAllSurveys();
        Optional<Survey> optionalSurvey =  surveys.stream()
                .filter(survey -> survey.getId().equals(surveyId))
                .findFirst();

        return optionalSurvey
                .map(Survey::getQuestions)
                .orElse(new ArrayList<>());

    }

    @Override
    public Question getQuestionOfSurvey(String surveyId, String questionId) {

        List<Question> questions = getAllQuestionsOfSurvey(surveyId);

        Optional<Question> optionalQuestion = questions.stream()
                .filter(
                        question -> question.getId().equals(questionId))
                .findFirst();

        return optionalQuestion.orElse(NOT_AVAILABLE_QUESTION);
    }

    @Override
    public String addNewSurveyQuestion(String surveyId, Question question) {

        List<Question> questions = getAllQuestionsOfSurvey(surveyId);

        String questionId = String.valueOf(
                SECURE_RANDOM.nextInt(10000)
        );
        question.setId(questionId);
        questions.add(question);
        return questionId;
    }

    @Override
    public void deleteQuestionOfSurvey(String surveyId, String questionId) {

        List<Question> questions = getAllQuestionsOfSurvey(surveyId);

        questions.removeIf(
                question -> question.getId().equals(questionId)
        );
    }
}
