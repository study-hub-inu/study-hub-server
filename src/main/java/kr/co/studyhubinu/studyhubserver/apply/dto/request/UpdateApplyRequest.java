package kr.co.studyhubinu.studyhubserver.apply.dto.request;

import kr.co.studyhubinu.studyhubserver.apply.enums.Inspection;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateApplyRequest {

    private Long userId;
    private Long studyId;
    private Inspection inspection;

    @Builder
    public UpdateApplyRequest(Long userId, Long studyId, Inspection inspection) {
        this.userId = userId;
        this.studyId = studyId;
        this.inspection = inspection;
    }
}
