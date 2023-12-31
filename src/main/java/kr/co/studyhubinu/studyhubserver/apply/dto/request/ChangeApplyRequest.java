package kr.co.studyhubinu.studyhubserver.apply.dto.request;

import kr.co.studyhubinu.studyhubserver.apply.enums.Inspection;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ChangeApplyRequest {

    private Long userId;

    private Long studyId;

    private Inspection inspection;

    @Builder
    public ChangeApplyRequest(Long userId, Long studyId, Inspection inspection) {
        this.userId = userId;
        this.studyId = studyId;
        this.inspection = inspection;
    }
}
