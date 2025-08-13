package com.example.myapp.workflow;

import com.example.myapp.service.impl.SurveyService;
import org.springframework.stereotype.Component;

@Component
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
