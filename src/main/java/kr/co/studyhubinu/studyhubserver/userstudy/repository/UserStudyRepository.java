package kr.co.studyhubinu.studyhubserver.userstudy.repository;

import kr.co.studyhubinu.studyhubserver.userstudy.domain.UserStudyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStudyRepository extends JpaRepository<UserStudyEntity, Long> {
}
