package kr.co.studyhubinu.studyhubserver.email.repository;

import kr.co.studyhubinu.studyhubserver.email.domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
