package kr.co.studyhubinu.studyhubserver.study.repository;

import kr.co.studyhubinu.studyhubserver.study.domain.StudyPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyPostRepository extends JpaRepository<StudyPost, Long> {
}
