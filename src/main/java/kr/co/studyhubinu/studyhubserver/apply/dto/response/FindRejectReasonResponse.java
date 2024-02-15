package kr.co.studyhubinu.studyhubserver.apply.dto.response;

import lombok.Getter;

@Getter
public class FindRejectReasonResponse {

    private final String title;
    private final String rejectReason;

    public FindRejectReasonResponse(String title, String rejectReason) {
        this.title = title;
        this.rejectReason = rejectReason;
    }
}
