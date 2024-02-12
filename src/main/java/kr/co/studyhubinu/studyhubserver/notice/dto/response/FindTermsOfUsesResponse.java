package kr.co.studyhubinu.studyhubserver.notice.dto.response;

import lombok.Getter;

@Getter
public class FindTermsOfUsesResponse {

    private final Long terms_of_use_id;
    private final String title;
    private final String article;
    private final String content;

    public FindTermsOfUsesResponse(Long terms_of_use_id, String title, String article, String content) {
        this.terms_of_use_id = terms_of_use_id;
        this.title = title;
        this.article = article;
        this.content = content;
    }
}
