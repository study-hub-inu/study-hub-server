package kr.co.studyhubinu.studyhubserver.config.jwt;

import lombok.Getter;

@Getter
public class JwtProperties {
    public static final int EXPIRATION_TIME = 864000000; // 10일
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}