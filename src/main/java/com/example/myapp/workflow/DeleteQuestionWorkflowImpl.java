package com.example.myapp.workflow;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import java.time.Duration;

public class DeleteQuestionWorkflowImpl implements DeleteQuestionWorkflow {

    private final SurveyActivities activities = Workflow.newActivityStub(
            SurveyActivities.class,
            ActivityOptions.newBuilder()
                    .setStartToCloseTimeout(Duration.ofSeconds(2))
                    .build());

    @Override
    public boolean deleteQuestion(String surveyId, String questionId) {
        return activities.deleteQuestion(surveyId, questionId);
    }
}
