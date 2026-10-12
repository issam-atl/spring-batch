package com.issam.batch.student;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.JobExecutionException;
import org.springframework.batch.core.job.parameters.InvalidJobParametersException;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;

import org.springframework.batch.core.launch.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.launch.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobRestartException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    @SuppressWarnings("removal")
    private final JobLauncher jobLauncher;
    private final Job job;

    @PostMapping
    public ResponseEntity<String> importCsvToDBJob() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis())
                .toJobParameters();
        CompletableFuture.runAsync(() -> {
            try {
                jobLauncher.run(job, jobParameters);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return ResponseEntity.accepted().body("El proceso de importación ha comenzado en segundo plano.");
    }
}

