package kr.co.studyhubinu.studyhubserver.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.studyhubinu.studyhubserver.config.jwt.JwtAuthenticationFilter;
import kr.co.studyhubinu.studyhubserver.config.jwt.JwtAuthorizationFilter;
import kr.co.studyhubinu.studyhubserver.config.jwt.JwtProvider;
import kr.co.studyhubinu.studyhubserver.support.fixture.UserEntityFixture;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.dto.request.SignUpRequest;
import kr.co.studyhubinu.studyhubserver.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private JwtAuthorizationFilter jwtAuthorizationFilter;




//    @Test
//    void 닉네임을_중복검사한다() throws Exception {
//        String url = "/api/users/duplication-nickname";
//        String nickName = "lee";
//
//        BDDMockito.given(userService.nicknameDuplicationValid(nickName));
//    }

}
