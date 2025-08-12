package com.example.myapp.workflow.activities;

import com.example.myapp.model.Question;

public interface SurveyActivities {
    String addQuestion(String surveyId, Question question);
}
