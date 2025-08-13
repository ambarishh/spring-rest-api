package com.example.myapp.config;

import io.temporal.client.WorkflowClient;
import io.temporal.common.RetryOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.temporal.worker.WorkerFactory;

import java.time.Duration;


@Configuration
public class TemporalConfig {
    public static final String TASK_QUEUE = "SURVEY_TASK_QUEUE";


    @Bean
    public WorkflowClient workflowClient() {
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        return WorkflowClient.newInstance(service);
    }

    @Bean
    public WorkerFactory workerFactory(WorkflowClient workflowClient) {
        return WorkerFactory.newInstance(workflowClient);
    }

    @Bean
    public io.temporal.worker.WorkerOptions workerOptions() {
        return io.temporal.worker.WorkerOptions.newBuilder()
                .setMaxConcurrentWorkflowTaskExecutionSize(100)
                .setMaxConcurrentActivityExecutionSize(100)
                .build();
    }

    @Bean
    public io.temporal.client.WorkflowOptions workflowOptions() {
        return io.temporal.client.WorkflowOptions.newBuilder()
                .setTaskQueue(TASK_QUEUE)
                .setWorkflowExecutionTimeout(Duration.ofHours(1))
                .setWorkflowRunTimeout(Duration.ofMinutes(10))
                .setWorkflowTaskTimeout(Duration.ofSeconds(30))
                .setRetryOptions(
                        RetryOptions.newBuilder()
                                .setInitialInterval(Duration.ofSeconds(1))
                                .setMaximumInterval(Duration.ofMinutes(1))
                                .setBackoffCoefficient(2.0)
                                .setMaximumAttempts(3)
                                .setDoNotRetry(
                                        IllegalArgumentException.class.getName(),
                                        NullPointerException.class.getName()
                                )
                                .build()
                )
                .build();
    }

}
