package kr.co.studyhubinu.studyhubserver.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.studyhubinu.studyhubserver.email.validate.InuEmail;
import kr.co.studyhubinu.studyhubserver.user.dto.data.SignUpInfo;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class SignUpRequest {

    @Schema(description = "유저 이메일", example = "xxx@inu.ac.kr")
    @InuEmail(message = "이메일 형식에 맞지 않습니다. (인천대학교 이메일 주소만 가능)")
    @NotBlank
    private String email;

    @Schema(description = "유저 비밀번호", example = "asd123")
    @Pattern(
            regexp = "^(?=.*[!@#$%^&*?~_]).{10,}$",
            message = "비밀번호는 10자 이상이어야 하며, 하나 이상의 특수문자를 포함해야 합니다."
    )
    @NotBlank
    private String password;

    @Schema(description = "유저 닉네임", example = "lee")
    @NotBlank
    private String nickname;

    @Schema(description = "전공", example = "COMPUTER")
    private MajorType major;

    @Schema(description = "유저 성별", example = "FEMALE")
    private GenderType gender;


    public SignUpInfo toService() {
        return new SignUpInfo(nickname, email, password, major, gender);
    }

}