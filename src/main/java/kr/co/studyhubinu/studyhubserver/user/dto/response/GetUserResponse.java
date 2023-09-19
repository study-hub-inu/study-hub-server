package kr.co.studyhubinu.studyhubserver.user.dto.response;

import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Getter;

@Getter
public class GetUserResponse {

    private Long userId;
    private String email;
    private String nickname;
    private String imaUrl;
    private MajorType major;
    private GenderType gender;

    public GetUserResponse(UserEntity user) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.imaUrl = user.getImaUrl();
        this.major = user.getMajor();
        this.gender = user.getGender();
    }
}
