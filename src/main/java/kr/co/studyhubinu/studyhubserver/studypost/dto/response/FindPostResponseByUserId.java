package kr.co.studyhubinu.studyhubserver.studypost.dto.response;

import kr.co.studyhubinu.studyhubserver.studypost.dto.data.PostDataByUserId;
import lombok.Getter;
import org.springframework.data.domain.Slice;

@Getter
public class FindPostResponseByUserId {

    Long totalCount;
    Slice<PostDataByUserId> posts;

    public FindPostResponseByUserId(Long totalCount, Slice<PostDataByUserId> posts) {
        this.totalCount = totalCount;
        this.posts = posts;
    }
}
