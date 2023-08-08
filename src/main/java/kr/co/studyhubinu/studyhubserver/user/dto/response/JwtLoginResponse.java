package kr.co.studyhubinu.studyhubserver.user.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JwtLoginResponse {

    private String accessToken;
    private String refreshToken;

    @Builder
    public JwtLoginResponse(String accessToken, String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}