package com.udemy.primeiroprojetospringbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

    @Bean
    public Job imprimirOlaJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("imprimirOlaJob", jobRepository)
                .start(step)
                .build();
    }

    @Bean
    public Step imprimeOlaStep(JobRepository jobRepository, PlatformTransactionManager manager) {
        return new StepBuilder("imprimirOlaStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Olá, Mundo!!");
                    return RepeatStatus.FINISHED;
                }, manager)
                .build();
    }
}
