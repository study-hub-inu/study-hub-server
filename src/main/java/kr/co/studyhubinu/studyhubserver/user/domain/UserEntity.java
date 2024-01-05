package kr.co.studyhubinu.studyhubserver.user.domain;

import kr.co.studyhubinu.studyhubserver.common.domain.BaseTimeEntity;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UpdateUserInfo;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import kr.co.studyhubinu.studyhubserver.apply.domain.ApplyEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class UserEntity extends BaseTimeEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String nickname;


    @Enumerated(EnumType.STRING)
    private MajorType major;

    @Enumerated(EnumType.STRING)
    private GenderType gender;

    @Column(name = "image_url", length = 1024)
    private String imageUrl;

    @Builder
    public UserEntity(Long id, String email, String password, String nickname, String imageUrl, MajorType major, GenderType gender) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.major = major;
        this.gender = gender;
    }

    @Builder
    public UserEntity(String email, String password, String nickname, String imaUrl, MajorType major, GenderType gender) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.imageUrl = imaUrl;
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

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
