package kr.co.studyhubinu.studyhubserver.user.dto.data;

import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.dto.request.GeneralSignUpRequest;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.GradeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@NoArgsConstructor
public class GeneralSignUpInfo {
    private String nickName;
    private String email;
    private String password;
    private GenderType gender;
    private GradeType grade;

    private String jwtToken;

    public UserEntity changeEntity(BCryptPasswordEncoder bCryptPasswordEncoder) {
        return UserEntity.builder()
                .email(email)
                .password(bCryptPasswordEncoder.encode(password))
                .gender(gender)
                .grade(grade)
                .build();
    }

    public GeneralSignUpInfo(String nickName, String email, String password, GenderType gender, GradeType grade) {
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.grade = grade;
    }

    public GeneralSignUpInfo(GeneralSignUpRequest generalSignUpRequest, String token) {
        this.nickName = generalSignUpRequest.getNickName();
        this.email = generalSignUpRequest.getEmail();
        this.password = generalSignUpRequest.getPassword();
        this.gender = generalSignUpRequest.getGender();
        this.grade = generalSignUpRequest.getGrade();
        this.jwtToken = token;
    }

    @Getter
    @AllArgsConstructor
    public static class UserId {
        private final Long id;
    }
}