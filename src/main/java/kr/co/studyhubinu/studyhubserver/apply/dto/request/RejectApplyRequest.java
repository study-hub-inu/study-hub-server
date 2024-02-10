package kr.co.studyhubinu.studyhubserver.apply.dto.request;

import kr.co.studyhubinu.studyhubserver.apply.domain.RejectEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class RejectApplyRequest {

    @NotNull(message = "스터디 아이디는 필수입니다!")
    private Long studyId;
    @NotNull(message = "거절된 유저 아이디는 필수입니다!")
    private Long rejectedUserId;
    @NotBlank(message = "거절 내용 작성은 필수입니다!")
    private String rejectReason;

    public RejectApplyRequest(Long studyId, Long rejectedUserId, String rejectReason) {
        this.studyId = studyId;
        this.rejectedUserId = rejectedUserId;
        this.rejectReason = rejectReason;
    }

    public RejectEntity toRejectEntity() {
        return RejectEntity.builder()
                .studyId(studyId)
                .rejectedUserId(rejectedUserId)
                .rejectReason(rejectReason)
                .build();
    }
}
