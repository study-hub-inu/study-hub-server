package kr.co.studyhubinu.studyhubserver.email.dto.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MailInfo {

    private String email;

    @Builder
    public MailInfo(String email) {
        this.email = email;
    }
}
