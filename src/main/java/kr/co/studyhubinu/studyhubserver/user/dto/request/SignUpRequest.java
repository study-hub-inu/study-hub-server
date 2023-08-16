package kr.co.studyhubinu.studyhubserver.user.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.studyhubinu.studyhubserver.email.validate.InuEmail;
import kr.co.studyhubinu.studyhubserver.user.dto.data.SignUpInfo;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.GradeType;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class SignUpRequest {

    @Schema(description = "유저 이름", example = "홀길동")
    @NotBlank
    private String nickName;

    @Schema(description = "유저 이메일", example = "xxx@inu.ac.kr")
    @InuEmail(message = "이메일 형식에 맞지 않습니다. (인천대학교 이메일 주소만 가능)")
    @NotBlank
    private String email;

    @Schema(description = "유저 비밀번호", example = "asd123")
    @NotBlank
    private String password;

    @Schema(description = "유저 성별", example = "FEMALE")
    @NotBlank
    private GenderType gender;

    @Schema(description = "유저 학년", example = "FOURTH")
    @NotBlank
    private GradeType grade;

    public SignUpInfo toService() {
        return new SignUpInfo(nickName, email, password, gender, grade);
    }

}