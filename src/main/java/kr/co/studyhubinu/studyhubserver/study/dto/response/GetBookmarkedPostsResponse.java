package kr.co.studyhubinu.studyhubserver.study.dto.response;

import kr.co.studyhubinu.studyhubserver.study.dto.data.GetBookmarkedPostsData;
import lombok.Getter;
import org.springframework.data.domain.Slice;

@Getter
public class GetBookmarkedPostsResponse {
    Long totalCount;
    Slice<GetBookmarkedPostsData> getBookmarkedPostsData;

    public GetBookmarkedPostsResponse(Long totalCount, Slice<GetBookmarkedPostsData> getBookmarkedPostsData) {
        this.totalCount = totalCount;
        this.getBookmarkedPostsData = getBookmarkedPostsData;
    }
}
