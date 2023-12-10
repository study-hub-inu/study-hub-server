package kr.co.studyhubinu.studyhubserver.support.fixture;

import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;

public enum UserEntityFixture {
    DONGWOO("dongwoo@inu.ac.kr", "asd12345!!", "DongWoo", MajorType.COMPUTER_SCIENCE_ENGINEERING, GenderType.MALE),
    YEONGJAE("yeongjae@inu.ac.kr", "asd12345@@", "LilJay", MajorType.COMPUTER_SCIENCE_ENGINEERING, GenderType.MALE),
    JOOWON("joowon@inu.ac.kr", "asd12345??", "Juser0", MajorType.COMPUTER_SCIENCE_ENGINEERING, GenderType.MALE);


    private final String email;
    private final String password;
    private final String nickname;
    private final MajorType majorType;
    private final GenderType genderType;

    UserEntityFixture(String email, String password, String nickname, MajorType majorType, GenderType genderType) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.majorType = majorType;
        this.genderType = genderType;
    }

    public UserEntity UserEntity_생성(Long id) {
        return UserEntity.builder()
                .id(id)
                .email(this.email)
                .password(this.password)
                .nickname(this.nickname)
                .major(this.majorType)
                .gender(this.genderType)
                .build();
    }

    public UserEntity UserEntity_생성() {
        return UserEntity.builder()
                .email(this.email)
                .password(this.password)
                .nickname(this.nickname)
                .major(this.majorType)
                .gender(this.genderType)
                .build();
    }

}
