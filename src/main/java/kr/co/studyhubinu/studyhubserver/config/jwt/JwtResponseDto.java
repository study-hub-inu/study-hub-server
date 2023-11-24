package kr.co.studyhubinu.studyhubserver.config.jwt;

import lombok.Builder;
import lombok.Getter;

@Getter
public class JwtResponseDto {

    private String accessToken;
    private String refreshToken;

    @Builder
    public JwtResponseDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
