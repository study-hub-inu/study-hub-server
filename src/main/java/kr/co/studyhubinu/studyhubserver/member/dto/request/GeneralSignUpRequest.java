package kr.co.studyhubinu.studyhubserver.member.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.studyhubinu.studyhubserver.member.dto.data.GeneralSignUpInfo;
import kr.co.studyhubinu.studyhubserver.member.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.member.enums.GradeType;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class GeneralSignUpRequest {

    @Schema(description = "유저 이름", example = "홀길동")
    @NotBlank
    private String nickName;

    @Schema(description = "유저 이메일", example = "xxx@gmail.com")
    @Email
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

    public GeneralSignUpInfo toDomain() {
        return new GeneralSignUpInfo(nickName, email, password, gender, grade);
    }

}