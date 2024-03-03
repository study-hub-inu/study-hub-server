package kr.co.studyhubinu.studyhubserver.apply.dto.response;

import kr.co.studyhubinu.studyhubserver.apply.dto.data.RejectApplyUserData;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Slice;

@Getter
public class FindRejectApplyResponse {

    Long totalCount;
    Slice<RejectApplyUserData> rejectApplyUserData;

    @Builder
    public FindRejectApplyResponse(Long totalCount, Slice<RejectApplyUserData> rejectApplyUserData) {
        this.totalCount = totalCount;
        this.rejectApplyUserData = rejectApplyUserData;
    }
}
