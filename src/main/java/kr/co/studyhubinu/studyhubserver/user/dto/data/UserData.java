package kr.co.studyhubinu.studyhubserver.user.dto.data;

import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Getter;

@Getter
public class UserData {
    private Long userId;
    private MajorType major;
    private String nickname;
    private String imageUrl;

    public UserData(Long userId, MajorType major, String nickname, String imageUrl) {
        this.userId = userId;
        this.major = major;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
    }
}
