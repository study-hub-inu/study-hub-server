package kr.co.studyhubinu.studyhubserver.config.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("/api/jwt")
public class JwtController {

    private final JwtProvider jwtProvider;

    @PostMapping("/accessToken")
    public ResponseEntity<String> accessTokenIssued(@RequestBody JwtDto jwtDto) {
        String accessToken =  jwtProvider.reissuedAccessToken(jwtDto);

        return ResponseEntity.ok(accessToken);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<String> refreshTokenIssued(@RequestBody JwtDto jwtDto) {
        String refreshToken = jwtProvider.reissuedRefreshToken(jwtDto);

        return ResponseEntity.ok(refreshToken);
    }

}
