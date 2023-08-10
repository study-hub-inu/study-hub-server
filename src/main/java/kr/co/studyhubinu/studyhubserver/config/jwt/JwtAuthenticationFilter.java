package kr.co.studyhubinu.studyhubserver.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.studyhubinu.studyhubserver.config.auth.PrincipalDetails;
import kr.co.studyhubinu.studyhubserver.user.dto.data.GeneralSignUpInfo;
import kr.co.studyhubinu.studyhubserver.user.dto.request.GeneralSignUpRequest;
import kr.co.studyhubinu.studyhubserver.util.CustomResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Slf4j
@Component
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private GeneralSignUpRequest generalSignUpRequest = new GeneralSignUpRequest();
    @Value("${jwt.secret}")
    private String SECRET;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/api/users/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper om = new ObjectMapper();

        try {
            generalSignUpRequest = om.readValue(request.getInputStream(), GeneralSignUpRequest.class);
        } catch(Exception e) {
            e.printStackTrace();
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(generalSignUpRequest.getEmail(), generalSignUpRequest.getPassword());

        return authenticationManager.authenticate(authenticationToken);
    }

        @Override
        protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult){
            PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

            String jwtToken = JWT.create()
                    .withSubject(principalDetails.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis()+(JwtProperties.EXPIRATION_TIME)))
                    .withClaim("email", principalDetails.getUser().getEmail())
                    .withClaim("id", principalDetails.getUser().getId())
                .sign(Algorithm.HMAC512(SECRET));

        GeneralSignUpInfo generalSignUpInfo = new GeneralSignUpInfo(generalSignUpRequest, jwtToken);

        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);
        log.info(jwtToken);
        CustomResponseUtil.success(response, generalSignUpInfo);
    }
}