package kr.co.studyhubinu.studyhubserver.apply.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.studyhubinu.studyhubserver.apply.domain.RejectEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class RejectApplyRequest {

    @Schema(description = "스터디 아이디")
    @NotNull(message = "스터디 아이디는 필수입니다!")
    private Long studyId;

    @Schema(description = "거절당하는 유저 아이디")
    @NotNull(message = "거절된 유저 아이디는 필수입니다!")
    private Long rejectedUserId;

    @Schema(description = "거절 사유", example = "ㄴㄴ")
    @NotBlank(message = "거절 사유는 필수입니다!")
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
