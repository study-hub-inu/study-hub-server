package kr.co.studyhubinu.studyhubserver.bookmark.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetDoBookmarkResponse {

    private boolean created;

    public GetDoBookmarkResponse(boolean created) {
        this.created = created;
    }
}
