package kr.co.studyhubinu.studyhubserver.user.dto.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateNicknameInfo {
    private Long userId;
    private String nickname;
    private boolean auth;

    @Builder
    public UpdateNicknameInfo(Long userId, String nickname, boolean auth) {
        this.userId = userId;
        this.nickname = nickname;
        this.auth = auth;
    }
}
