package com.example.myapp.workflow;

import com.example.myapp.model.Question;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface AddQuestionWorkflow {
    @WorkflowMethod
    String addQuestion(String surveyId, Question question);
}
