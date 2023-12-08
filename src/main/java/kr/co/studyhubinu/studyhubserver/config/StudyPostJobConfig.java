package kr.co.studyhubinu.studyhubserver.config;

import kr.co.studyhubinu.studyhubserver.studypost.repository.StudyPostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class StudyPostJobConfig {

    public final JobBuilderFactory jobBuilderFactory;

    public final StepBuilderFactory stepBuilderFactory;

    private final StudyPostRepository studyPostRepository;

    @Bean("studyPostJob")
    public Job studyPostJob() {             // 빌더로 초기화하는 과정
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
        return stepBuilderFactory.get("changeBoardStatus")
                .tasklet(studyPostTasklet())
                .build();
    }

    @StepScope
    @Bean("studyPostTasklet")
    public Tasklet studyPostTasklet() {
        return (contribution, chunkContext) -> {
            log.info("***********실행중 실행중 실행중**************");
            return RepeatStatus.FINISHED;
        };
    }

}