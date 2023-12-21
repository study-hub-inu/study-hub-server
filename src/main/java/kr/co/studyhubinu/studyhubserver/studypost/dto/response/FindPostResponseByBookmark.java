package kr.co.studyhubinu.studyhubserver.studypost.dto.response;

import kr.co.studyhubinu.studyhubserver.studypost.dto.data.PostDataByBookmark;
import lombok.Getter;
import org.springframework.data.domain.Slice;

@Getter
public class FindPostResponseByBookmark {
    Long totalCount;
    Slice<PostDataByBookmark> getBookmarkedPostsData;

    public FindPostResponseByBookmark(Long totalCount, Slice<PostDataByBookmark> getBookmarkedPostsData) {
        this.totalCount = totalCount;
        this.getBookmarkedPostsData = getBookmarkedPostsData;
    }
}
