package kr.co.studyhubinu.studyhubserver.config;

import kr.co.studyhubinu.studyhubserver.studypost.repository.StudyPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
@RequiredArgsConstructor
public class StudyPostJobConfig {

    public final JobBuilderFactory jobBuilderFactory;

    public final StepBuilderFactory stepBuilderFactory;

    private final StudyPostRepository studyPostRepository;

    @Bean("studyPostJob")
    public Job studyPostJob() {
        return jobBuilderFactory.get("studyPostJob")
                .incrementer(new RunIdIncrementer())
                .start(changeStudyPostCloseByDeadline())
                .on("FAILED")
                .stopAndRestart(changeStudyPostCloseByDeadline())
                .on("*")
                .end()
                .end()
                .build();
    }

    @JobScope
    @Bean("changeStudyPostCloseByDeadline")
    public Step changeStudyPostCloseByDeadline() {
        return stepBuilderFactory.get("changeStudyPostCloseByDeadline")
                .tasklet(studyPostTasklet())
                .build();
    }

    @StepScope
    @Bean("studyPostTasklet")
    public Tasklet studyPostTasklet() {
        return (contribution, chunkContext) -> {
            studyPostRepository.closeStudyPostsByStartDate(LocalDate.now());
            return RepeatStatus.FINISHED;
        };
    }

}