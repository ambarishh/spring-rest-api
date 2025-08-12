package com.example.myapp.workflow;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface DeleteQuestionWorkflow {
    @WorkflowMethod
    boolean deleteQuestion(String surveyId, String questionId);
}
