package com.example.myapp.workflow;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface SurveyActivities {
    @ActivityMethod
    boolean deleteQuestion(String surveyId, String questionId);
}
