package kr.co.studyhubinu.studyhubserver.study.dto.response;

import kr.co.studyhubinu.studyhubserver.study.dto.data.GetMyPostData;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
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
