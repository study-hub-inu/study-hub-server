package kr.co.studyhubinu.studyhubserver.user.dto.response;

import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Getter;

@Getter
public class GetUserResponse {

    private String nickname;
    private MajorType major;
    private GenderType gender;
    private String email;
    private String imageUrl;
    public GetUserResponse(UserEntity user) {
        this.nickname = user.getNickname();
        this.major = user.getMajor();
        this.gender = user.getGender();
        this.email = user.getEmail();
        this.imageUrl = user.getImageUrl();
    }
}
