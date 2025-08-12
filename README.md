# REST with Spring Boot

Simple CRUD application using Spring Boot 3 & Java 17. The application now uses Temporal to orchestrate workflows when adding new survey questions.

### URLs


#### GET

- http://localhost:8080/surveys
- http://localhost:8080/surveys/Survey1
- http://localhost:8080/surveys/Survey1/questions
- http://localhost:8080/surveys/Survey1/questions/Question1

##### Response

```
[
    {
        "id": "Survey1",
        "title": "My Favorite Survey",
        "description": "Description of the Survey",
        "questions": [
            {
                "id": "Question1",
                "description": "Most Popular Cloud Platform Today",
                "options": [
                    "AWS",
                    "Azure",
                    "Google Cloud",
                    "Oracle Cloud"
                ],
                "ans": "AWS"
            },
            {
                "id": "Question2",
                "description": "Fastest Growing Cloud Platform",
                "options": [
                    "AWS",
                    "Azure",
                    "Google Cloud",
                    "Oracle Cloud"
                ],
                "ans": "Google Cloud"
            },
            {
                "id": "Question3",
                "description": "Most Popular DevOps Tool",
                "options": [
                    "Kubernetes",
                    "Docker",
                    "Terraform",
                    "Azure DevOps"
                ],
                "ans": "Kubernetes"
            }
        ]
    }
]

```

#### DELETE

- http://localhost:8080/surveys/Survey1/questions/Question1

##### POST

**URL**: http://localhost:8080/surveys/Survey1/questions/
**Header**: Content-Type:application/json

**Request Body**
```
{
    "description": "Your Favorite Cloud Platform",
    "options": [
        "AWS",
        "Azure",
        "Google Cloud",
        "Oracle Cloud"
    ],
    "ans": "Google Cloud"
}

```

##### PUT

**URL**: http://localhost:8080/surveys/Survey1/questions/Question1
**Header**: Content-Type:application/json
**Request Body**
```
{
    "id": "Question1",
    "description": "Most Popular Cloud Platform Today Change",
    "options": [
        "AWS",
        "Azure",
        "Google Cloud",
        "Oracle Cloud"
    ],
    "ans": "Google Cloud"
}

```

