package kr.co.studyhubinu.studyhubserver.user.dto.data;

import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.dto.request.SignUpRequest;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@NoArgsConstructor
public class SignUpInfo {

    private String email;
    private String password;
    private String nickname;
    private MajorType major;
    private GenderType gender;
    private String accessToken;

    private String refreshToken;

    public UserEntity toEntity(BCryptPasswordEncoder bCryptPasswordEncoder) {
        return UserEntity.builder()
                .email(email)
                .password(bCryptPasswordEncoder.encode(password))
                .nickname(nickname)
                .major(major)
                .gender(gender)
                .build();
    }

    public SignUpInfo(String nickname, String email, String password, MajorType major, GenderType gender) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.major = major;
        this.gender = gender;
    }

    public SignUpInfo(SignUpRequest signUpRequest, String accessToken, String refreshToken) {
        this.nickname = signUpRequest.getNickname();
        this.email = signUpRequest.getEmail();
        this.password = signUpRequest.getPassword();
        this.gender = signUpRequest.getGender();
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

}