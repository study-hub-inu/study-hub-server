package kr.co.studyhubinu.studyhubserver.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UpdateNicknameInfo;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class UpdateNicknameRequest {

    @NotBlank(message = "닉네임은 있어야 합니다")
    @Size(max = 10, message = "닉네임은 10자 이하여야 합니다")
    private String nickname;

    @Schema(description = "해당 유저가 기존 닉네임 중복검사를 실시했는지")
    @NotNull(message = "접근권한이 없는 유저입니다")
    private boolean auth;

    public UpdateNicknameInfo toService(Long userId) {
        return UpdateNicknameInfo.builder()
                .userId(userId)
                .nickname(nickname)
                .auth(auth)
                .build();
    }
}
