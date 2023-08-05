package kr.co.studyhubinu.studyhubserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StudyHubServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyHubServerApplication.class, args);
    }

}
