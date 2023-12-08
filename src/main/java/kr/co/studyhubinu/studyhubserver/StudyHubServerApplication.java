package kr.co.studyhubinu.studyhubserver;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableBatchProcessing
@EnableScheduling
public class StudyHubServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyHubServerApplication.class, args);
    }

}
