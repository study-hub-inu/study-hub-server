package kr.co.studyhubinu.studyhubserver.config.resolver;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import kr.co.studyhubinu.studyhubserver.config.jwt.JwtProperties;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


@Component
@RequiredArgsConstructor
public class UserIdArgumentResolver implements HandlerMethodArgumentResolver {

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
            String jwtToken = webRequest.getHeader(JwtProperties.ACCESS_HEADER_STRING);
            if (jwtToken == null) {
                return new UserId(null);
            }
            jwtToken = jwtToken.replace(JwtProperties.TOKEN_PREFIX, "");
            Long id = (JWT.require(Algorithm.HMAC512(SECRET)).build().verify(jwtToken).getClaim("id")).asLong();
            return new UserId(id);
        } catch (Exception e) {
            return null;
        }
    }
}
