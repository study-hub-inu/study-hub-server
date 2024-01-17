package kr.co.studyhubinu.studyhubserver.notification.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CreateFcmTokenRequest {

    @Schema(description = "FCM 토큰값", example = "asdfasd")
    @NotBlank(message = "FCM 토큰값은 필수 입니다!")
    private String fcmToken;

    @Builder
    public CreateFcmTokenRequest(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}
