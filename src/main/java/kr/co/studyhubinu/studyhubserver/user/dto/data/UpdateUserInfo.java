package kr.co.studyhubinu.studyhubserver.user.dto.data;

import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateUserInfo {

    private Long userId;
    private String nickname;
    private String imaUrl;
    private MajorType major;

    @Builder
    public UpdateUserInfo(Long userId, String nickname, String imaUrl, MajorType major) {
        this.userId = userId;
        this.nickname = nickname;
        this.imaUrl = imaUrl;
        this.major = major;
    }
}
