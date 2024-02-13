package kr.co.studyhubinu.studyhubserver.reject.repository;

import kr.co.studyhubinu.studyhubserver.reject.dto.response.RejectReasonResponse;

public interface RejectRepositoryCustom {

    RejectReasonResponse findByStudyIdAndRejectedUserId(Long studyId, Long rejectedUserId);
}
