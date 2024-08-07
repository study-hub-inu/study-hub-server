package kr.co.studyhubinu.studyhubserver.email.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.studyhubinu.studyhubserver.email.dto.data.ValidMailInfo;
import kr.co.studyhubinu.studyhubserver.email.validate.NormalEmail;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class MailValidRequest {

    @Schema(description = "이메일 주소", example = "kdw990202@inu.ac.kr")
    @NotBlank
    @NormalEmail
//    @InuEmail(message = "이메일 형식에 맞지 않습니다. (인천대학교 이메일 주소만 가능)")
    private String email;

    private String authCode;

    public ValidMailInfo toService() {
        return ValidMailInfo.builder()
                .email(email)
                .authCode(authCode)
                .build();
    }

}
