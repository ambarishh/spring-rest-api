package com.example.myapp.workflow;

import com.example.myapp.model.Question;
import com.example.myapp.workflow.activities.SurveyActivities;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class AddQuestionWorkflowImpl implements AddQuestionWorkflow {

    private final SurveyActivities activities = Workflow.newActivityStub(
            SurveyActivities.class,
            ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(5)).build()
    );

    @Override
    public String addQuestion(String surveyId, Question question) {
        return activities.addQuestion(surveyId, question);
    }
}
