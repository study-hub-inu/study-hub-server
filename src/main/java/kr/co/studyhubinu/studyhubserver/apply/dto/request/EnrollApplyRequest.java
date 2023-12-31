package kr.co.studyhubinu.studyhubserver.apply.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EnrollApplyRequest {

    private Long studyId;

    @Builder
    public EnrollApplyRequest(Long studyId) {
        this.studyId = studyId;
    }
}
