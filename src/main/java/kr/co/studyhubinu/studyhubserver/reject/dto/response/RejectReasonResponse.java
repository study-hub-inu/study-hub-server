package kr.co.studyhubinu.studyhubserver.reject.dto.response;

import lombok.Getter;

@Getter
public class RejectReasonResponse {

    private final String studyTitle;
    private final String reason;

    public RejectReasonResponse(String studyTitle, String reason) {
        this.studyTitle = studyTitle;
        this.reason = reason;
    }
}
