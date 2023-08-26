package kr.co.studyhubinu.studyhubserver.config.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("/api/jwt")
public class JwtController {

    private final JwtProvider jwtProvider;

    @GetMapping("/accessToken")
    public ResponseEntity<?> accessTokenIssued(@RequestBody JwtDto jwtDto) {
        String accessToken = jwtProvider.reissuedAccessToken(jwtDto);

        return new ResponseEntity<>(accessToken, HttpStatus.OK);
    }

    @GetMapping("/refreshToken")
    public ResponseEntity<?> refreshTokenIssued(@RequestBody JwtDto jwtDto) {
        String refreshToken = jwtProvider.reissuedRefreshToken(jwtDto);

        return new ResponseEntity<>(refreshToken, HttpStatus.OK);
    }

}
