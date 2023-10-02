package kr.co.studyhubinu.studyhubserver.userpost.repository;

import kr.co.studyhubinu.studyhubserver.study.repository.StudyPostRepositoryCustom;
import kr.co.studyhubinu.studyhubserver.userpost.domain.UserPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPostRepository extends JpaRepository<UserPostEntity, Long> {
}
