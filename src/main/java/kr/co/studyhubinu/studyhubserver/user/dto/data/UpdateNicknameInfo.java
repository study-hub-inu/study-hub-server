package kr.co.studyhubinu.studyhubserver.user.dto.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateNicknameInfo {
    private Long userId;
    private String nickname;

    @Builder
    public UpdateNicknameInfo(Long userId, String nickname) {
        this.userId = userId;
        this.nickname = nickname;
    }
}
