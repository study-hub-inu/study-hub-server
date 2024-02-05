package kr.co.studyhubinu.studyhubserver.apply.dto.request;

import lombok.Getter;

@Getter
public class RejectApplyRequest {

    private final Long studyId;
    private final String rejectReason;

    public RejectApplyRequest(Long studyId, String rejectReason) {
        this.studyId = studyId;
        this.rejectReason = rejectReason;
    }
}
