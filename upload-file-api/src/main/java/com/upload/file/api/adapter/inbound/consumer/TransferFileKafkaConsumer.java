package com.upload.file.api.adapter.inbound.consumer;

import com.upload.file.api.domain.helper.JobParameterBuilderHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransferFileKafkaConsumer {

    private final Job job;

    private final JobLauncher jobLauncher;

    public TransferFileKafkaConsumer(Job job, JobLauncher jobLauncher) {
        this.job = job;
        this.jobLauncher = jobLauncher;
    }

    @KafkaListener(topics = "transactionfile-saved", groupId = "transaction-group")
    public void consumeStringMessage(String transactionFileId) {
        try {
            System.out.println("Received message: " + transactionFileId);
            // Aqui você pode adicionar lógica para processar a mensagem recebida.
            final JobParameters jobParameters = JobParameterBuilderHelper.create(transactionFileId);

            final JobExecution jobExecution = jobLauncher.run(job, jobParameters);
        } catch (JobInstanceAlreadyCompleteException
                | JobExecutionAlreadyRunningException
                | JobParametersInvalidException
                | JobRestartException e) {
            log.error("Job not started, status {} ", e.getMessage());
        }
    }
}
