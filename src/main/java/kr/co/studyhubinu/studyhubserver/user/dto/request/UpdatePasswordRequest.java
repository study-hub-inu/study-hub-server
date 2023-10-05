package kr.co.studyhubinu.studyhubserver.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UpdatePasswordInfo;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class UpdatePasswordRequest {

    @Schema(description = "유저 비밀번호", example = "asd123")
    @Pattern(
            regexp = "^(?=.*[!@#$%^&*?~_]).{10,}$",
            message = "비밀번호는 10자 이상이어야 하며, 하나 이상의 특수문자를 포함해야 합니다."
    )
    @NotBlank(message = "비밀번호는 입력하셔야 합니다")
    private String password;

    @Schema(description = "해당 유저가 기존 비밀번호를 확인했는지")
    @NotNull(message = "접근권한이 없는 유저입니다")
    private boolean auth;

    public UpdatePasswordInfo toService(Long userId) {
        return UpdatePasswordInfo.builder()
                .userId(userId)
                .password(password)
                .auth(auth)
                .build();
    }
}
