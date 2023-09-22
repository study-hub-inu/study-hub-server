package kr.co.studyhubinu.studyhubserver.user.dto.data;

import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateUserInfo {

    private Long userId;
    private String nickname;
    private String imageUrl;
    private MajorType major;

    @Builder
    public UpdateUserInfo(Long userId, String nickname, String imageUrl, MajorType major) {
        this.userId = userId;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.major = major;
    }
}
