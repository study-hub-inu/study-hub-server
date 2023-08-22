package kr.co.studyhubinu.studyhubserver.config.resolver;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import kr.co.studyhubinu.studyhubserver.config.jwt.JwtProperties;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserId;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserIdArgumentResolver implements HandlerMethodArgumentResolver {

    private final UserRepository userRepository;
    @Value("${jwt.secret}")
    private String SECRET;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType() == UserId.class;
    }

    @Override
    public UserId resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                                    NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        try {
            String jwtToken = Objects.requireNonNull(webRequest.getHeader(JwtProperties.ACCESS_HEADER_STRING)).replace(JwtProperties.TOKEN_PREFIX, "");
            Long id = Long.parseLong(JWT.require(Algorithm.HMAC512(SECRET)).build().verify(jwtToken).getClaim("id").asString());

            return new UserId(id);
        } catch (Exception e) {
            log.info("UserIdArgumentResolver.resolveArgument() : {}", e.getMessage());
            return null;
        }
    }
}
