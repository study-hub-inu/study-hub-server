package kr.co.studyhubinu.studyhubserver.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import kr.co.studyhubinu.studyhubserver.exception.token.TokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Transactional
@Service
public class JwtProvider {

    private final RedisTemplate<Long, String> redisTemplate;

    @Value("${jwt.secret}")
    private String SECRET;

    public JwtResponseDto createJwtResponseDto(Long id) {
        return JwtResponseDto.builder()
                .accessToken(createAccessToken(id))
                .refreshToken(createRefreshToken(id))
                .build();
    }

    private String createAccessToken(Long id) {
        String jwtToken = JWT.create()
                .withSubject("accessToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000L * 60 * 60))
                .withClaim("id", id)
                .sign(Algorithm.HMAC512(SECRET));
        return JwtProperties.TOKEN_PREFIX + jwtToken;
    }

    private String createRefreshToken(Long id) {
        String jwtToken = JWT.create()
                .withSubject("refreshToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 7 )) // 1주일
                .withClaim("id", id)
                .sign(Algorithm.HMAC512(SECRET));
        redisTemplate.opsForValue().set(id, jwtToken, 1000L * 60 * 60 * 24 * 7 * 4, TimeUnit.MILLISECONDS);
        return JwtProperties.TOKEN_PREFIX + jwtToken;
    }

    public String reissuedAccessToken(JwtDto jwtDto) {
        String refreshToken = jwtDto.getRefreshToken().replace(JwtProperties.TOKEN_PREFIX, "");
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET)).build().verify(refreshToken);
        Long id = decodedJWT.getClaim("id").asLong();

        if(refreshToken.equals(redisTemplate.opsForValue().get(id))) {
            return createAccessToken(id);
        }
        throw new TokenNotFoundException();
    }

    public String reissuedRefreshToken(JwtDto jwtDto) {
        String refreshToken = jwtDto.getRefreshToken().replace(JwtProperties.TOKEN_PREFIX, "");
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET)).build().verify(refreshToken);
        Long id = decodedJWT.getClaim("id").asLong();

        if(refreshToken.equals(redisTemplate.opsForValue().get(id))) {
            String token = createRefreshToken(id).replace(JwtProperties.TOKEN_PREFIX, "");
            redisTemplate.delete(id);
            redisTemplate.opsForValue().set(id, token, 1000L * 60 * 60 * 24 * 7 * 4, TimeUnit.MILLISECONDS);
            return token;
        }
        throw new TokenNotFoundException();
    }
}
