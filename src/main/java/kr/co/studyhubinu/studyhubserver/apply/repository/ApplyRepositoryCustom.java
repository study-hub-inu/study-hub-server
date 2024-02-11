package kr.co.studyhubinu.studyhubserver.apply.repository;

import kr.co.studyhubinu.studyhubserver.apply.dto.data.ApplyUserData;
import kr.co.studyhubinu.studyhubserver.apply.dto.data.ParticipateApplyData;
import kr.co.studyhubinu.studyhubserver.apply.dto.request.FindApplyRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplyRepositoryCustom {

    List<ApplyUserData> findByStudy(Long studyId, Pageable pageable);

    List<ParticipateApplyData> findByUserIdAndInspection(Long userId, Pageable pageable);

    List<ApplyUserData> findStudyByIdAndInspection(FindApplyRequest request, Pageable pageable);
}
