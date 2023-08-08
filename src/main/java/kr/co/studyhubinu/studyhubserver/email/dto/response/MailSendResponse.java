package kr.co.studyhubinu.studyhubserver.email.dto.response;

import lombok.Getter;

@Getter
public class MailSendResponse {

    private String code;

    public MailSendResponse(String code) {
        this.code = code;
    }
}
