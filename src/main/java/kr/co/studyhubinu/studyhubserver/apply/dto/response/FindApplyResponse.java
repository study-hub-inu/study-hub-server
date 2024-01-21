package kr.co.studyhubinu.studyhubserver.apply.dto.response;

import kr.co.studyhubinu.studyhubserver.apply.dto.data.ApplyUserData;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Slice;

@Getter
public class FindApplyResponse {

    Long totalCount;
    Slice<ApplyUserData> applyUserData;

    @Builder
    public FindApplyResponse(Long totalCount, Slice<ApplyUserData> applyUserData) {
        this.totalCount = totalCount;
        this.applyUserData = applyUserData;
    }
}
