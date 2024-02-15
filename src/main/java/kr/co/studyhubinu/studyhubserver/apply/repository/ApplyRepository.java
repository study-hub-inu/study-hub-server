package kr.co.studyhubinu.studyhubserver.apply.repository;

import kr.co.studyhubinu.studyhubserver.apply.domain.ApplyEntity;
import kr.co.studyhubinu.studyhubserver.apply.enums.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplyRepository extends JpaRepository<ApplyEntity, Long>, ApplyRepositoryCustom {
    Optional<ApplyEntity> findByUserIdAndStudyId(Long userId, Long studyId);

    List<ApplyEntity> findByUserId(Long userId);

    Long countByUserIdAndInspection(Long userId, Inspection accept);

    Long countByUserId(Long userId);
}
