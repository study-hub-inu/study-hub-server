package kr.co.studyhubinu.studyhubserver.apply.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FindApplyRequest {

    private Long studyId;

    @Builder
    public FindApplyRequest(Long studyId) {
        this.studyId = studyId;
    }
}
