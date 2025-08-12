package com.example.myapp.config;

import com.example.myapp.service.impl.SurveyService;
import com.example.myapp.workflow.AddQuestionWorkflowImpl;
import com.example.myapp.workflow.activities.SurveyActivitiesImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.myapp.service.utils.Constants.SURVEY_TASK_QUEUE;

@Configuration
public class TemporalConfig {

    @Bean
    public WorkflowServiceStubs workflowServiceStubs() {
        return WorkflowServiceStubs.newInstance();
    }

    @Bean
    public WorkflowClient workflowClient(WorkflowServiceStubs service) {
        return WorkflowClient.newInstance(service);
    }

    @Bean
    public WorkerFactory workerFactory(WorkflowClient client,
                                       SurveyService surveyService) {
        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker(SURVEY_TASK_QUEUE);
        worker.registerWorkflowImplementationTypes(AddQuestionWorkflowImpl.class);
        worker.registerActivitiesImplementations(new SurveyActivitiesImpl(surveyService));
        factory.start();
        return factory;
    }
}
