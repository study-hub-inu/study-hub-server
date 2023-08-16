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
    private String nickName;
    private String email;
    private String password;
    private String nickname;
    private MajorType major;
    private GenderType gender;
    private String jwtToken;

    public UserEntity toEntity(BCryptPasswordEncoder bCryptPasswordEncoder) {
        return UserEntity.builder()
                .email(email)
                .password(bCryptPasswordEncoder.encode(password))
                .nickname(nickname)
                .major(major)
                .gender(gender)
                .build();
    }

    public SignUpInfo(String nickName, String email, String password, String nickname, MajorType major, GenderType gender) {
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.major = major;
        this.gender = gender;
    }

    public SignUpInfo(SignUpRequest signUpRequest, String token) {
        this.nickName = signUpRequest.getNickName();
        this.email = signUpRequest.getEmail();
        this.password = signUpRequest.getPassword();
        this.gender = signUpRequest.getGender();
        this.jwtToken = token;
    }

}