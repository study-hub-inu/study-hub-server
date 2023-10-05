package kr.co.studyhubinu.studyhubserver.support.fixture;

import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;

public enum UserEntityFixture {
    DONGWOO("xxx@inu.ac.kr", "asd123", "DongWoo", "www.asdasdasds" , MajorType.COMPUTER_SCIENCE_ENGINEERING, GenderType.MALE),
    YEONGJAE("xxx@inu.ac.kr", "asd123", "LilJay", "www.asdasdddas" , MajorType.COMPUTER_SCIENCE_ENGINEERING, GenderType.MALE),
    JOOWON("xxx@inu.ac.kr", "asd123", "Juser0", "www.asdasdddas" , MajorType.COMPUTER_SCIENCE_ENGINEERING, GenderType.MALE);


    private final String email;
    private final String password;
    private final String nickname;
    private final String imgUrl;
    private final MajorType majorType;
    private final GenderType genderType;

    UserEntityFixture(String email, String password, String nickname, String imgUrl, MajorType majorType, GenderType genderType) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.imgUrl = imgUrl;
        this.majorType = majorType;
        this.genderType = genderType;
    }

    public UserEntity UserEntity_생성(Long id) {
        return UserEntity.builder()
                .id(id)
                .email(this.email)
                .password(this.password)
                .nickname(this.nickname)
                .imageUrl(this.imgUrl)
                .major(this.majorType)
                .gender(this.genderType)
                .build();
    }

}
