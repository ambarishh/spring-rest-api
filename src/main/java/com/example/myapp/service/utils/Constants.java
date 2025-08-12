package com.example.myapp.service.utils;

import com.example.myapp.model.Question;
import com.example.myapp.model.Survey;

import java.security.SecureRandom;

public class Constants {
    public static final Survey NOT_AVAILABLE_SURVEY = new Survey();
    public static final Question NOT_AVAILABLE_QUESTION = new Question();

    public static final SecureRandom SECURE_RANDOM = new SecureRandom();
    public static final String SURVEY_TASK_QUEUE = "SURVEY_TASK_QUEUE";
}
