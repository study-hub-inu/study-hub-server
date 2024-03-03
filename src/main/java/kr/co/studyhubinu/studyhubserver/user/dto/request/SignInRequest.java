package kr.co.studyhubinu.studyhubserver.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.studyhubinu.studyhubserver.email.validate.InuEmail;
import kr.co.studyhubinu.studyhubserver.email.validate.NormalEmail;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class SignInRequest {

    @Schema(description = "유저 이메일", example = "studyHub@inu.ac.kr")
    @NotBlank
    @NormalEmail
    private String email;

    @Schema(description = "유저 비밀번호", example = "asdasdasd!!")
    @NotBlank
    private String password;

    @Builder
    public SignInRequest(String email, String password){
        this.email = email;
        this.password = password;
    }
}