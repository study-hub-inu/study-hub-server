package kr.co.studyhubinu.studyhubserver.apply.dto.request;

import kr.co.studyhubinu.studyhubserver.apply.enums.Inspection;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FindApplyRequest {

    private final Long studyId;
    private final Inspection inspection;

    @Builder
    public FindApplyRequest(Long studyId, Inspection inspection) {
        this.studyId = studyId;
        this.inspection = inspection;
    }
}
