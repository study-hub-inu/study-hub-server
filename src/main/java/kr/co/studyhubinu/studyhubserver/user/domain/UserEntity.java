package kr.co.studyhubinu.studyhubserver.user.domain;

import kr.co.studyhubinu.studyhubserver.common.domain.BaseTimeEntity;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UpdateUserInfo;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    private MajorType major;

    private GenderType gender;

    private String imageUrl;

    @Builder
    public UserEntity(Long id, String email, String password, String nickname, MajorType major, GenderType gender) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
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
}
