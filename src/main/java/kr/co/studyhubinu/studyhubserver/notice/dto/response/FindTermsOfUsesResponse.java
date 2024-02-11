package kr.co.studyhubinu.studyhubserver.notice.dto.response;

import lombok.Getter;

@Getter
public class FindTermsOfUsesResponse {

    private final Long terms_of_use_id;
    private final String title;
    private final String content;

    public FindTermsOfUsesResponse(Long terms_of_use_id, String title, String content) {
        this.terms_of_use_id = terms_of_use_id;
        this.title = title;
        this.content = content;
    }
}
