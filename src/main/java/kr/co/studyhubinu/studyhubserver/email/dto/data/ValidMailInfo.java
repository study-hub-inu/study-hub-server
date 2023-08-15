package kr.co.studyhubinu.studyhubserver.email.dto.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ValidMailInfo {

    private String email;
    private String authCode;

    @Builder
    public ValidMailInfo(String email, String authCode) {
        this.email = email;
        this.authCode = authCode;
    }
}
