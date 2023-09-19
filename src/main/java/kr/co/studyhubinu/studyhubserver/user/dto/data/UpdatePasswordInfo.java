package kr.co.studyhubinu.studyhubserver.user.dto.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdatePasswordInfo {
    private Long userId;
    private String password;

    @Builder
    public UpdatePasswordInfo(Long userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
