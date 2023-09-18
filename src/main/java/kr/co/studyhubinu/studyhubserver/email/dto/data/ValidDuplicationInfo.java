package kr.co.studyhubinu.studyhubserver.email.dto.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ValidDuplicationInfo {
    private String email;

    @Builder
    public ValidDuplicationInfo(String email) {
        this.email = email;
    }
}
