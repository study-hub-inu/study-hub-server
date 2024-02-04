package kr.co.studyhubinu.studyhubserver.notice.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FindNoticeResponse {

    private final Long noticeId;
    private final String title;
    private final String content;

    @Builder
    public FindNoticeResponse(Long noticeId, String title, String content) {
        this.noticeId = noticeId;
        this.title = title;
        this.content = content;
    }
}
