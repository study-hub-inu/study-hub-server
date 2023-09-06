package kr.co.studyhubinu.studyhubserver.config.jwt;

import com.auth0.jwt.exceptions.TokenExpiredException;
import kr.co.studyhubinu.studyhubserver.config.auth.PrincipalDetails;
import kr.co.studyhubinu.studyhubserver.exception.token.TokenNotFoundException;
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

// 시큐리티가 filter 가지고있는데 그 필터중에 BasicAuthenticationFilter 라는 것이 있다.
// 권한이나 인증이 필요한 특정 주소를 요청했을때 위 필터를 무조건 타게 되어있음
// 만약 권한이나 인증이 필요한 주소가 아니라면 이 필터를 안탄다.
@Slf4j
@Component
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final JwtProvider jwtProvider;

    @Value("${jwt.secret}")
    private String SECRET;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        super(authenticationManager);
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if(isHeaderVerify(request, response)) {
            String accessToken = request.getHeader(JwtProperties.ACCESS_HEADER_STRING);

            try {
                PrincipalDetails principalDetails = jwtProvider.accessTokenVerify(accessToken);

                Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch(TokenExpiredException e) {
                throw new TokenNotFoundException();
            }
        }

        chain.doFilter(request, response);
    }

    private boolean isHeaderVerify(HttpServletRequest request, HttpServletResponse response) {
        String accessHeader = request.getHeader(JwtProperties.ACCESS_HEADER_STRING);

        if(accessHeader == null || accessHeader.startsWith(JwtProperties.TOKEN_PREFIX)) {
            return false;
        }
        return true;
    }
}