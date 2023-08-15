package kr.co.studyhubinu.studyhubserver.email.dto.response;

import lombok.Getter;

@Getter
public class ValidEmailResponse {

    private boolean validResult;

    public ValidEmailResponse(boolean auth) {
        this.validResult = auth;
    }
}
