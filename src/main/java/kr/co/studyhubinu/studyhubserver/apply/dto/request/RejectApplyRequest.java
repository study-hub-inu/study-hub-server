package kr.co.studyhubinu.studyhubserver.apply.dto.request;

import kr.co.studyhubinu.studyhubserver.apply.domain.RejectEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RejectApplyRequest {

    private Long studyId;
    private Long rejectedUserId;
    private String rejectReason;

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
