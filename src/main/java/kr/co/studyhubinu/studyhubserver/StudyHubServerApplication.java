package kr.co.studyhubinu.studyhubserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableCaching
public class StudyHubServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyHubServerApplication.class, args);
    }

}
