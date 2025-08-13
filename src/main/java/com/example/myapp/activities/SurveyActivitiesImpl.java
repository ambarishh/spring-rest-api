package com.example.myapp.activities;

import com.example.myapp.activities.SurveyActivities;
import com.example.myapp.service.impl.SurveyService;
import io.temporal.spring.boot.ActivityImpl;
import org.springframework.stereotype.Component;

@Component
@ActivityImpl(workers = "survey-worker")
public class SurveyActivitiesImpl implements SurveyActivities {

    private final SurveyService surveyService;

    public SurveyActivitiesImpl(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @Override
    public boolean deleteQuestion(String surveyId, String questionId) {
        return surveyService.deleteQuestionOfSurvey(surveyId, questionId);
    }
}
