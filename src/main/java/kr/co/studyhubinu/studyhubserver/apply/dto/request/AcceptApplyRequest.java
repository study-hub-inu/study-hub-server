package kr.co.studyhubinu.studyhubserver.apply.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class AcceptApplyRequest {

    @Schema(description = "스터디 아이디")
    @NotNull(message = "스터디 아이디는 필수입니다!")
    private Long studyId;

    @Schema(description = "거절당하는 유저 아이디")
    @NotNull(message = "거절된 유저 아이디는 필수입니다!")
    private Long rejectedUserId;

}
