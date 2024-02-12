package kr.co.studyhubinu.studyhubserver.apply.dto.response;

import kr.co.studyhubinu.studyhubserver.apply.dto.data.ParticipateApplyData;
import lombok.Getter;
import org.springframework.data.domain.Slice;

@Getter
public class FindParticipateApplyResponse {
    private Long totalCount;
    Slice<ParticipateApplyData> participateStudyData;

    public FindParticipateApplyResponse(Long totalCount, Slice<ParticipateApplyData> participateStudyData) {
        this.totalCount = totalCount;
        this.participateStudyData = participateStudyData;
    }
}
