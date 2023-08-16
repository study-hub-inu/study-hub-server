package kr.co.studyhubinu.studyhubserver.user.dto.data;

import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.dto.request.SignUpRequest;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.GradeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@NoArgsConstructor
public class SignUpInfo {
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

    public SignUpInfo(String nickName, String email, String password, GenderType gender, GradeType grade) {
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.grade = grade;
    }

    public SignUpInfo(SignUpRequest signUpRequest, String token) {
        this.nickName = signUpRequest.getNickName();
        this.email = signUpRequest.getEmail();
        this.password = signUpRequest.getPassword();
        this.gender = signUpRequest.getGender();
        this.grade = signUpRequest.getGrade();
        this.jwtToken = token;
    }

    @Getter
    @AllArgsConstructor
    public static class UserId {
        private final Long id;
    }
}