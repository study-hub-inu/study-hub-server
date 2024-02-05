package kr.co.studyhubinu.studyhubserver.apply.dto.request;

import kr.co.studyhubinu.studyhubserver.apply.domain.RejectEntity;
import lombok.Getter;

@Getter
public class RejectApplyRequest {

    private final Long studyId;
    private final Long rejectedUserId;
    private final String rejectReason;

    public RejectApplyRequest(Long studyId, Long rejectedUserId, String rejectReason) {
        this.studyId = studyId;
        this.rejectedUserId = rejectedUserId;
        this.rejectReason = rejectReason;
    }

    public RejectEntity toRejectEntity() {
        return RejectEntity.builder()
                .studyId(studyId)
                .rejectedUserId(rejectedUserId)
                .rejectReason(rejectReason)
                .build();
    }
}
