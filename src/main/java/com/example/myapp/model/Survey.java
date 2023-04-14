package com.example.myapp.model;


import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Survey {

    private String id;
    private String title;
    private String description;
    private List<Question> questions;
}
