package kr.co.studyhubinu.studyhubserver.user.domain;

import kr.co.studyhubinu.studyhubserver.common.domain.BaseTimeEntity;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UpdatePasswordInfo;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UpdateUserInfo;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String nickname;

    private String imaUrl;

    @Enumerated(EnumType.STRING)
    private MajorType major;

    @Enumerated(EnumType.STRING)
    private GenderType gender;

    private String imageUrl;

    @Builder
    public UserEntity(Long id, String email, String password, String nickname, String imaUrl, MajorType major, GenderType gender) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.imaUrl = imaUrl;
        this.major = major;
        this.gender = gender;
    }


    public void update(UpdateUserInfo info) {
        this.nickname = info.getNickname();
        this.major = info.getMajor();
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateMajor(MajorType major) {
        this.major = major;
    }

    public void updatePassword(UpdatePasswordInfo info, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.password = bCryptPasswordEncoder.encode(info.getPassword());
    }
}
