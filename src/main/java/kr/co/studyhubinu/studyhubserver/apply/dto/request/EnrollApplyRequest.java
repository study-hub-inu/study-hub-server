package kr.co.studyhubinu.studyhubserver.apply.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EnrollApplyRequest {

    private Long studyId;
    private String introduce;

    @Builder
    public EnrollApplyRequest(Long studyId, String introduce) {
        this.studyId = studyId;
        this.introduce = introduce;
    }
}
