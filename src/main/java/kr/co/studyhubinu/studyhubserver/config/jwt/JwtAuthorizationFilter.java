package kr.co.studyhubinu.studyhubserver.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import kr.co.studyhubinu.studyhubserver.config.auth.PrincipalDetails;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.exception.UserException;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static kr.co.studyhubinu.studyhubserver.user.exception.UserErrorCode.USER_NOT_FOUND_EXCEPTION;

// 시큐리티가 filter 가지고있는데 그 필터중에 BasicAuthenticationFilter 라는 것이 있다.
// 권한이나 인증이 필요한 특정 주소를 요청했을때 위 필터를 무조건 타게 되어있음
// 만약 권한이나 인증이 필요한 주소가 아니라면 이 필터를 안탄다.
@Slf4j
@Component
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final UserRepository userRepository;
    @Value("${jwt.secret}")
    private String SECRET;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String jwtHeader = request.getHeader(JwtProperties.HEADER_STRING);

        if (jwtHeader == null || !jwtHeader.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        String jwtToken = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");

        String email =
                JWT.require(Algorithm.HMAC512(SECRET)).build().verify(jwtToken).getClaim("email").asString();

        if(email != null) {
            UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new UserException(USER_NOT_FOUND_EXCEPTION));

            // 인증은 토큰 검증시 끝. 인증을 하기 위해서가 아닌 스프링 시큐리티가 수행하는 권한처리를 위해
            // 아래와 같이 토큰을 만들어서 Authentication 객체를 강제로 만들고 그걸 세션에 저장
            PrincipalDetails principalDetails = new PrincipalDetails(userEntity);
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

            //강제로 시큐리티의 세션에 접근하여 Authentication 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
}