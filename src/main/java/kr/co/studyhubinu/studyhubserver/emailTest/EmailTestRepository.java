package kr.co.studyhubinu.studyhubserver.emailTest;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailTestRepository extends JpaRepository<EmailTestEntity, Long> {
    EmailTestEntity findByEmail(String email);
}
