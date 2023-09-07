package kr.co.studyhubinu.studyhubserver.config.jwt;

import lombok.Getter;

@Getter
public class JwtProperties {
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String ACCESS_HEADER_STRING = "Authorization";
    public static final String REFRESH_HEADER_STRING = "REFRESH_TOKEN";
}