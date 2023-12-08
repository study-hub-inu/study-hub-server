package kr.co.studyhubinu.studyhubserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
//@EnableBatchProcessing
//@EnableScheduling
public class StudyHubServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyHubServerApplication.class, args);
    }

}
