package com.example.myapp.workflows;

import com.example.myapp.activities.SurveyActivities;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.workflow.Workflow;

import java.time.Duration;

//no need of @Component since this is managed by Temporal
@WorkflowImpl(workers = "survey-worker")
public class DeleteQuestionWorkflowImpl implements DeleteQuestionWorkflow {

    private final ActivityOptions activityOptions = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(10))
            .setScheduleToCloseTimeout(Duration.ofMinutes(5))
            .setRetryOptions(
                    RetryOptions.newBuilder()
                            .setInitialInterval(Duration.ofSeconds(1))
                            .setMaximumInterval(Duration.ofMinutes(1))
                            .setBackoffCoefficient(2.0)
                            .setMaximumAttempts(3)
                            .build()
            ).build();


    private final SurveyActivities activities = Workflow.newActivityStub(
            SurveyActivities.class,
            activityOptions);

    @Override
    public boolean deleteQuestion(String surveyId, String questionId) {

        return activities.deleteQuestion(surveyId, questionId);
    }
}
