package kr.co.studyhubinu.studyhubserver.config.jwt;

import kr.co.studyhubinu.studyhubserver.user.dto.data.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("/api/jwt")
public class JwtController {

    private final JwtProvider jwtProvider;

    @PostMapping("/accessToken")
    public ResponseEntity<JwtResponseDto> accessTokenIssued(@RequestBody JwtDto jwtDto) {
        String accessToken =  jwtProvider.reissuedAccessToken(jwtDto);
        String refreshToken = jwtProvider.reissuedRefreshToken(jwtDto);

        return ResponseEntity.ok(new JwtResponseDto(accessToken, refreshToken));
    }
}
