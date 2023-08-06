package kr.co.studyhubinu.studyhubserver.member.repository;

import kr.co.studyhubinu.studyhubserver.member.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);

    UserEntity findByEmail(String email);
}
