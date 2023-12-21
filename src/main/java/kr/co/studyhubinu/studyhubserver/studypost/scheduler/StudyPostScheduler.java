package kr.co.studyhubinu.studyhubserver.studypost.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
public class StudyPostScheduler {

   private final JobLauncher jobLauncher;
   private final Job job;

    @Scheduled(cron = "0 0 4 * * *")
    public void runJob() {

        try{
            jobLauncher.run(
                    job, new JobParametersBuilder().addString("dateTime", LocalDateTime.now().toString()).toJobParameters()
            );
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
