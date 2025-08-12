package com.example.myapp.workflow.activities;

import com.example.myapp.model.Question;
import com.example.myapp.service.impl.SurveyService;

public class SurveyActivitiesImpl implements SurveyActivities {

    private final SurveyService surveyService;

    public SurveyActivitiesImpl(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @Override
    public String addQuestion(String surveyId, Question question) {
        return surveyService.addNewSurveyQuestion(surveyId, question);
    }
}
