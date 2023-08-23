package kr.co.studyhubinu.studyhubserver.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import kr.co.studyhubinu.studyhubserver.config.auth.PrincipalDetails;
import kr.co.studyhubinu.studyhubserver.exception.token.TokenNotFoundException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@RequiredArgsConstructor
@Transactional
@Service
public class JwtProvider {

    private final RedisTemplate redisTemplate;
    private final UserRepository userRepository;

    @Value("${jwt.secret}")
    private String SECRET;

    public String accessTokenCreate(Long id) {
        String jwtToken = JWT.create()
                .withSubject("accessToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000L * 60 * 3))
                .withClaim("id", id)
                .sign(Algorithm.HMAC512(SECRET));
        return JwtProperties.TOKEN_PREFIX + jwtToken;
    }

    public String refreshTokenCreate(Long id) {
        String jwtToken = JWT.create()
                .withSubject("refreshToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 7 )) // 1주일
                .withClaim("id", id)
                .sign(Algorithm.HMAC512(SECRET));
        redisTemplate.opsForValue().set(id, jwtToken, 1000L * 60 * 60 * 24 * 7 * 4);
        return JwtProperties.TOKEN_PREFIX + jwtToken;
    }

    public PrincipalDetails accessTokenVerify(String accessToken) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET)).build().verify(accessToken);
        Long id = decodedJWT.getClaim("id").asLong();
        UserEntity userEntity = UserEntity.builder().id(id).build();
        return new PrincipalDetails(userEntity);
    }

    public PrincipalDetails refreshTokenVerify(String refreshToken) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET)).build().verify(refreshToken);
        Long id = decodedJWT.getClaim("id").asLong();

        String tokenInRedis = String.valueOf(redisTemplate.opsForValue().get(id.toString()));

        if(refreshToken.equals(tokenInRedis)) {
            UserEntity userEntity = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
            return new PrincipalDetails(userEntity);
        }
        SecurityContextHolder.getContext().setAuthentication(null);
        return null;
    }

    public String reissuedAccessToken(JwtDto jwtDto) {
        String refreshToken = jwtDto.getRefreshToken();
        Long id = jwtDto.getId();

        if(refreshToken.equals(redisTemplate.opsForValue().get(id))) {
            return accessTokenCreate(id);
        }
        throw new TokenNotFoundException();
    }

    public String reissuedRefreshToken(JwtDto jwtDto) {
        String refreshToken = jwtDto.getRefreshToken();
        Long id = jwtDto.getId();

        if(refreshToken.equals(redisTemplate.opsForValue().get(id))) {
            String token = refreshTokenCreate(id);
            redisTemplate.opsForValue().set(id, token);
            return token;
        }
        throw new TokenNotFoundException();
    }
}
