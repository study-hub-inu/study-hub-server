package kr.co.studyhubinu.studyhubserver.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.studyhubinu.studyhubserver.email.validate.InuEmail;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class SignInRequest {

    @Schema(description = "유저 이메일", example = "xxx@inu.ac.kr")
    @NotBlank
    @InuEmail(message = "이메일 형식에 맞지 않습니다. (인천대학교 이메일 주소만 가능)")
    private String email;

    @Schema(description = "유저 비밀번호", example = "asd123")
    @NotBlank
    private String password;

    public SignInRequest(String email, String password){
        this.email = email;
        this.password = password;
    }
}