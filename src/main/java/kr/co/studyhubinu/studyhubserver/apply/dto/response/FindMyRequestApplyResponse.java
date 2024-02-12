package kr.co.studyhubinu.studyhubserver.apply.dto.response;

import kr.co.studyhubinu.studyhubserver.apply.dto.data.ParticipateApplyData;
import kr.co.studyhubinu.studyhubserver.apply.dto.data.RequestApplyData;
import lombok.Getter;
import org.springframework.data.domain.Slice;

@Getter
public class FindMyRequestApplyResponse {

    private Long totalCount;
    Slice<RequestApplyData> requestStudyData;

    public FindMyRequestApplyResponse(Long totalCount, Slice<RequestApplyData> requestStudyData) {
        this.totalCount = totalCount;
        this.requestStudyData = requestStudyData;
    }
}
