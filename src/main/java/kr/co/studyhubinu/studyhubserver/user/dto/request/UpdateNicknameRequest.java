package kr.co.studyhubinu.studyhubserver.user.dto.request;

import kr.co.studyhubinu.studyhubserver.user.dto.data.UpdateNicknameInfo;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class UpdateNicknameRequest {

    @NotBlank(message = "닉네임은 있어야 합니다")
    @Size(max = 10, message = "닉네임은 10자 이하여야 합니다")
    private String nickname;

    public UpdateNicknameInfo toService(Long userId) {
        return UpdateNicknameInfo.builder()
                .userId(userId)
                .nickname(nickname)
                .build();
    }
}
