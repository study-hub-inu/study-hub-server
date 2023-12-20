package kr.co.studyhubinu.studyhubserver.studypost.dto.response;

import kr.co.studyhubinu.studyhubserver.studypost.dto.data.PostDataByInquiry;
import lombok.Getter;
import org.springframework.data.domain.Slice;

@Getter
public class FindPostResponseByInquiry {

    Long totalCount;
    Slice<PostDataByInquiry> postDataByInquiries;

    public FindPostResponseByInquiry(Long totalCount, Slice<PostDataByInquiry> postDataByInquiries) {
        this.totalCount = totalCount;
        this.postDataByInquiries = postDataByInquiries;
    }
}
