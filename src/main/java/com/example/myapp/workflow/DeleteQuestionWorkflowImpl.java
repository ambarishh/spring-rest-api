package com.example.myapp.workflow;

import io.temporal.activity.ActivityOptions;
import io.temporal.client.WorkflowClient;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;

//no need of @Component since this is managed by Temporal
public class DeleteQuestionWorkflowImpl implements DeleteQuestionWorkflow {

    @Override
    public boolean deleteQuestion(String surveyId, String questionId) {

        // Create ActivityOptions directly in the workflow method
        ActivityOptions activityOptions = ActivityOptions.newBuilder()
                .setStartToCloseTimeout(Duration.ofSeconds(30))
                .setScheduleToCloseTimeout(Duration.ofMinutes(5))
                .setRetryOptions(
                        RetryOptions.newBuilder()
                                .setInitialInterval(Duration.ofSeconds(1))
                                .setMaximumInterval(Duration.ofMinutes(1))
                                .setBackoffCoefficient(2.0)
                                .setMaximumAttempts(3)
                                .build()
                ).build();


        SurveyActivities activities = Workflow.newActivityStub(
                SurveyActivities.class,
                activityOptions);
        return activities.deleteQuestion(surveyId, questionId);
    }
}
