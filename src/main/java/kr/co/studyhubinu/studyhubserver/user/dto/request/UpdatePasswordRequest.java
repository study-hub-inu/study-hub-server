package kr.co.studyhubinu.studyhubserver.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UpdatePasswordInfo;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class UpdatePasswordRequest {

    @Schema(description = "유저 비밀번호", example = "asd123")
    @Pattern(
            regexp = "^(?=.*[!@#$%^&*?~_]).{10,}$",
            message = "비밀번호는 10자 이상이어야 하며, 하나 이상의 특수문자를 포함해야 합니다."
    )
    @NotBlank
    private String password;

    public UpdatePasswordInfo toService(Long userId) {
        return UpdatePasswordInfo.builder()
                .userId(userId)
                .password(password)
                .build();
    }
}
