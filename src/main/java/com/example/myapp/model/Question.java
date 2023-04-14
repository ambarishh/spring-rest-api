package com.example.myapp.model;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Question {
    private String id;
    private String text;
    private List<String> options;
    private String ans;
}
