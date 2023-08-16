package kr.co.studyhubinu.studyhubserver.config.resolver;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import kr.co.studyhubinu.studyhubserver.config.jwt.JwtProperties;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserId;
import kr.co.studyhubinu.studyhubserver.user.exception.UserException;
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

import static kr.co.studyhubinu.studyhubserver.user.dto.data.SignUpInfo.*;
import static kr.co.studyhubinu.studyhubserver.user.exception.UserErrorCode.USER_NOT_FOUND_EXCEPTION;


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
                                                    NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        try {
            String jwtToken = Objects.requireNonNull(webRequest.getHeader(JwtProperties.HEADER_STRING)).replace(JwtProperties.TOKEN_PREFIX, "");
            String email = JWT.require(Algorithm.HMAC512(SECRET)).build().verify(jwtToken).getClaim("email").asString();
            UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new UserException(USER_NOT_FOUND_EXCEPTION));

            return new UserId(userEntity.getId());
        } catch (Exception e) {
            log.info("UserIdArgumentResolver.resolveArgument() : {}", e.getMessage());
            return null;
        }
    }
}
