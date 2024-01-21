package kr.co.studyhubinu.studyhubserver.apply.repository;

import kr.co.studyhubinu.studyhubserver.apply.domain.ApplyEntity;
import kr.co.studyhubinu.studyhubserver.study.domain.StudyEntity;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyRepository extends JpaRepository<ApplyEntity, Long>, ApplyRepositoryCustom {
    ApplyEntity findByUserIdAndStudyId(Long userId, Long studyId);

    List<ApplyEntity> findByUserId(Long userId);
}
