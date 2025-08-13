package com.example.myapp.workflow;

import com.example.myapp.config.TemporalConfig;
import com.example.myapp.workflow.DeleteQuestionWorkflowImpl;
import com.example.myapp.workflow.SurveyActivitiesImpl;
import io.temporal.activity.ActivityOptions;
import io.temporal.client.WorkflowClient;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import io.temporal.worker.WorkerOptions;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static com.example.myapp.config.TemporalConfig.TASK_QUEUE;


@Component
public class SurveyWorker {

    private static final Logger logger = LoggerFactory.getLogger(SurveyWorker.class);

    @Autowired
    private WorkerOptions workerOptions;
    @Autowired
    private WorkerFactory workerFactory;
    @Autowired
    private SurveyActivitiesImpl surveyActivities;

    @EventListener(ApplicationReadyEvent.class)
    public void startWorker() {

        Worker worker = workerFactory.newWorker(
                TemporalConfig.TASK_QUEUE,
                workerOptions
        );

        // Register the workflow implementation with the worker
        worker.registerWorkflowImplementationTypes(DeleteQuestionWorkflowImpl.class);

        // Register the activity implementation with the worker
        worker.registerActivitiesImplementations(surveyActivities);

        // Start the worker
        workerFactory.start();

        logger.info("Temporal Worker started for SURVEY_TASK_QUEUE");
    }
}
