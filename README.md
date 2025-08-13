# REST with Spring Boot

Simple CRUD application using Spring Boot 3 & Java 21.

## Prerequisites

- Docker and Docker Compose
- (Optional) Java 21 and Maven 3.9.6+ for local development without Docker

## Quick Start with Docker Compose

1. Build and start the application:
   ```bash
   docker-compose up --build
   ```

2. The application will be available at: http://localhost:8080

3. To run in detached mode:
   ```bash
   docker-compose up --build -d
   ```

4. To stop the application:
   ```bash
   docker-compose down
   ```

## Local Development (Without Docker)

1. Build the application:
   ```bash
   mvn clean package
   ```

2. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## API Reference

### REST API Endpoints

#### Create a Question
```bash
curl -X POST http://localhost:8080/surveys/123/questions/ \
-H "Content-Type: application/json" \
-d '{
  "id": "q1",
  "text": "What is your favorite programming language?",
  "options": ["Java", "Python", "JavaScript", "C++"],
  "ans": "Java"
}'
```

#### Get All Surveys
```bash
curl -X GET http://localhost:8080/surveys
```

#### Get Questions for a Survey
```bash
curl -X GET http://localhost:8080/surveys/Survey1/questions
```

#### Delete a Question
```bash
curl -X DELETE http://localhost:8080/surveys/Survey1/questions/Question1
```

## Troubleshooting

- If the application fails to start, check the logs:
  ```bash
  docker-compose logs survey-application
  ```
- The application includes health checks at `/actuator/health`
- To see the application logs in real-time:
  ```bash
  docker-compose logs -f survey-application
  ```