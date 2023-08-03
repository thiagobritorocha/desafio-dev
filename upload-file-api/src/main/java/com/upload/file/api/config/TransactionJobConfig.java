package com.upload.file.api.config;

import static com.upload.file.api.domain.utils.ConstantsUtils.*;

import com.upload.file.api.adapter.outbound.repository.transaction.TransactionEntity;
import com.upload.file.api.domain.listener.TransactionJobListener;
import com.upload.file.api.domain.listener.TransactionStepListener;
import com.upload.file.api.domain.usecase.transaction.TransactionProcessor;
import com.upload.file.api.domain.usecase.transaction.TransactionRead;
import com.upload.file.api.domain.usecase.transaction.TransactionWriter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@RequiredArgsConstructor
public class TransactionJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final TransactionJobListener transactionJobListener;
    private final StepBuilderFactory stepBuilderFactory;
    private final TransactionStepListener transactionStepListener;

    @Bean
    @Qualifier(QUALIFIER_JOB)
    public JobLauncher jobLauncherTransactions(JobRepository jobRepository) {
        final SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
        simpleJobLauncher.setJobRepository(jobRepository);
        simpleJobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return simpleJobLauncher;
    }

    @Bean
    public Job job() {
        return jobBuilderFactory
                .get(DEFAULT_JOB_STEP)
                .incrementer(new RunIdIncrementer())
                .listener(transactionJobListener)
                .flow(step())
                .end()
                .build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory
                .get(DEFAULT_STEP)
                .<String, List<TransactionEntity>>chunk(1000)
                .reader(createTransactionRead())
                .processor(createTransactionsProcessor())
                .writer(createTransactionsWriter())
                .listener(transactionStepListener)
                .build();
    }

    @Bean
    @StepScope
    public TransactionRead createTransactionRead() {
        return new TransactionRead();
    }

    @Bean
    public TransactionProcessor createTransactionsProcessor() {
        return new TransactionProcessor();
    }

    @Bean
    public TransactionWriter createTransactionsWriter() {
        return new TransactionWriter();
    }
}
