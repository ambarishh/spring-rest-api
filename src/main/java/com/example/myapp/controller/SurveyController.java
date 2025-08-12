package com.example.myapp.controller;

import com.example.myapp.model.Question;
import com.example.myapp.model.Survey;
import com.example.myapp.service.impl.SurveyService;
import com.example.myapp.workflow.DeleteQuestionWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.example.myapp.service.utils.Constants.NOT_AVAILABLE_QUESTION;
import static com.example.myapp.service.utils.Constants.NOT_AVAILABLE_SURVEY;

@RestController
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private WorkflowClient workflowClient;

    @GetMapping("/surveys")
    public List<Survey> getAllSurveys(){
        return surveyService.getAllSurveys();
    }

    @GetMapping("/surveys/{surveyId}")
    public ResponseEntity<Object> getSurvey(
            @PathVariable String surveyId){

        Survey survey = surveyService.getServey(surveyId);

        if(survey.equals(NOT_AVAILABLE_SURVEY)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(survey, HttpStatus.OK);
    }

    @GetMapping("/surveys/{surveyId}/questions")
    public ResponseEntity<Object> getAllQuestionsOfSurvey(
            @PathVariable String surveyId
    ){
        List<Question> questions = surveyService.getAllQuestionsOfSurvey(surveyId);

        return new ResponseEntity(questions, HttpStatus.OK);
    }

    @GetMapping("/surveys/{surveyId}/questions/{questionId}")
    public ResponseEntity<Object> getQuestionOfSurvey(
            @PathVariable String surveyId,
            @PathVariable String questionId
    ){
        Question question = surveyService.getQuestionOfSurvey(surveyId, questionId);

        if(question.equals(NOT_AVAILABLE_QUESTION)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(question, HttpStatus.OK);
    }

    @PostMapping("/surveys/{surveyId}/questions/")
    public ResponseEntity<Object> addNewSurveyQuestion(
        @PathVariable String  surveyId,
        @RequestBody Question question
    ){
        String questionId = surveyService.addNewSurveyQuestion(surveyId, question);
        URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{questionId}")
                            .buildAndExpand(questionId)
                            .toUri();

        return new ResponseEntity(location, HttpStatus.CREATED);
    }

    @DeleteMapping("/surveys/{surveyId}/questions/{questionId}")
    public ResponseEntity<Object> deleteQuestionOfSurvey(
            @PathVariable String surveyId,
            @PathVariable String questionId
    ){
        DeleteQuestionWorkflow workflow = workflowClient.newWorkflowStub(
                DeleteQuestionWorkflow.class,
                WorkflowOptions.newBuilder().setTaskQueue("SURVEY_TASK_QUEUE").build());

        boolean removed = workflow.deleteQuestion(surveyId, questionId);
        if(!removed){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.noContent().build();
    }

}
