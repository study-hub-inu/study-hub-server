package kr.co.studyhubinu.studyhubserver.config.jwt;

import lombok.Getter;

@Getter
public class JwtDto {

    private Long id;
    private String refreshToken;
}
