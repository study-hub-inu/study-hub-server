package kr.co.studyhubinu.studyhubserver.member.repository;

import kr.co.studyhubinu.studyhubserver.member.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
}
