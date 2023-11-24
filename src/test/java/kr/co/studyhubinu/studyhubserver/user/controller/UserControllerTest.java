package kr.co.studyhubinu.studyhubserver.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.studyhubinu.studyhubserver.config.WebConfig;
import kr.co.studyhubinu.studyhubserver.config.jwt.JwtResponseDto;
import kr.co.studyhubinu.studyhubserver.user.dto.request.SignInRequest;
import kr.co.studyhubinu.studyhubserver.user.dto.request.SignUpRequest;
import kr.co.studyhubinu.studyhubserver.user.dto.request.UpdateUserRequest;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import kr.co.studyhubinu.studyhubserver.user.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UserController.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebConfig.class)
})
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    ObjectMapper objectMapper = new ObjectMapper();

    static Stream<Arguments> passwordParameters() {
        return Stream.of(
                Arguments.of("10자 미만, 특수 문자 미포함 비밀번호", "universe"),
                Arguments.of("10자 미만, 특수 문자 포함 비밀번호", "universe@"),
                Arguments.of("10자 이상, 특수 문자 미 포함 비밀번호", "inuUniversity")
        );
    }

    static Stream<Arguments> emailParameters() {
        return Stream.of(
                Arguments.of("인천대 이메일이 형식이 아닌 이메일", "kdw@naver.com"),
                Arguments.of("이메일 앞의 문자가 없는 경우", "@inu.ac.kr")
        );
    }

    @Test
    void 회원가입_성공() throws Exception {
        // given
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .email("201801686@inu.ac.kr")
                .nickname("포이리에")
                .gender(GenderType.MALE)
                .password("DustinPorier!")
                .build();

        // when
        ResultActions resultActions = mockMvc.perform(
                post("/api/v1/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signUpRequest))
        );

        // then
        resultActions.andExpect(status().isCreated())
                .andDo(print());
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("passwordParameters")
    void 잘못된_형식_비밀번호로_회원가입시_예외발생(String testName, String password) throws Exception {
        // given
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .email("201801686@inu.ac.kr")
                .nickname("포이리에")
                .gender(GenderType.MALE)
                .password(password)
                .build();

        // when
        ResultActions resultActions = mockMvc.perform(
                post("/api/v1/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signUpRequest))
                        .characterEncoding("UTF-8")
        );

        MvcResult mvcResult = resultActions.andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString(UTF_8);

        // then
        resultActions.andExpect(status().is4xxClientError())
                .andDo(print());
        assertTrue(responseBody.contains("비밀번호는 10자 이상이어야 하며, 하나 이상의 특수문자를 포함해야 합니다."));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("emailParameters")
    void 잘못된_형식_이메일로_회원가입시_예외발생(String testName, String email) throws Exception {
        // given
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .email(email)
                .nickname("포이리에")
                .gender(GenderType.MALE)
                .password("dwdwdwdwdwdw@")
                .build();

        // when
        ResultActions resultActions = mockMvc.perform(
                post("/api/v1/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signUpRequest))
                        .characterEncoding("UTF-8")
        );

        MvcResult mvcResult = resultActions.andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString(UTF_8);

        // then
        resultActions.andExpect(status().is4xxClientError())
                .andDo(print());
        assertTrue(responseBody.contains("이메일 형식에 맞지 않습니다. (인천대학교 이메일 주소만 가능)"));
    }

    @Test
    void 로그인_성공시_토큰을_반환한다() throws Exception {
        // given
        SignInRequest signInRequest = SignInRequest.builder()
                .email("kdw@inu.ac.kr")
                .password("dwdwdwdwdwdw@")
                .build();
        when(userService.loginUser(any())).thenReturn(JwtResponseDto.builder()
                .accessToken("Bearer AccessToken")
                .refreshToken("Bearer RefreshToken")
                .build());

        // when
        ResultActions resultActions = mockMvc.perform(
                post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signInRequest))
                        .characterEncoding("UTF-8")
        );
        MvcResult mvcResult = resultActions.andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString(UTF_8);

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print());
        assertTrue(responseBody.contains("Bearer AccessToken"));
        assertTrue(responseBody.contains("Bearer RefreshToken"));
    }

//    @Test
//    void 회원정보_수정_실패_잘못된_() {
//        // given
//        UpdateUserRequest updateUserRequest = UpdateUserRequest.builder()
//                .nickname("폰노이만")
//                .major(MajorType.COMPUTER_SCIENCE_ENGINEERING)
//                .imageUrl("adasdasdasdasda")
//                .build();
//
//
//        // when
//
//
//
//        // then
//    }

}