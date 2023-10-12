package kr.co.studyhubinu.studyhubserver.study;

import kr.co.studyhubinu.studyhubserver.study.domain.StudyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<StudyEntity, Long> {
}
