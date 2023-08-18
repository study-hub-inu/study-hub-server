package kr.co.studyhubinu.studyhubserver.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UpdateUserInfo;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UpdateUserRequest {

    @Schema(description = "수정할 닉네임", example = "닉네임")
    @NotBlank
    private String nickname;

    @Schema(description = "수정할 전공", example = "컴퓨터공학부")
    @NotBlank
    private MajorType major;

    public UpdateUserInfo toService(Long userId) {

        return UpdateUserInfo.builder()
                .userId(userId)
                .nickname(nickname)
                .major(major)
                .build();
    }
}