package com.example.myapp.workflows;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface DeleteQuestionWorkflow {
    @WorkflowMethod
    boolean deleteQuestion(String surveyId, String questionId);

//    @QueryMethod
//    public String details();
}
