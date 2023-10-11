package kr.co.studyhubinu.studyhubserver.studypost.dto.response;

import kr.co.studyhubinu.studyhubserver.studypost.dto.data.GetMyPostData;
import lombok.Getter;
import org.springframework.data.domain.Slice;

@Getter
public class GetMyPostResponse {

    Long totalCount;
    Slice<GetMyPostData> getMyPostData;

    public GetMyPostResponse(Long totalCount, Slice<GetMyPostData> getMyPostData) {
        this.totalCount = totalCount;
        this.getMyPostData = getMyPostData;
    }
}
