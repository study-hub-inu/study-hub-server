package kr.co.studyhubinu.studyhubserver.user.dto.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdatePasswordInfo {
    private Long userId;
    private String password;
    private boolean auth;

    @Builder
    public UpdatePasswordInfo(Long userId, String password, boolean auth) {
        this.userId = userId;
        this.password = password;
        this.auth = auth;
    }
}
