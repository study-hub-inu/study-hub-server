package kr.co.studyhubinu.studyhubserver.config.jwt;

import org.springframework.beans.factory.annotation.Value;

public class JwtProperties {
    @Value("${jwt.secret}")
    public static String SECRET;
    public static final int EXPIRATION_TIME = 864000000; // 10일
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}