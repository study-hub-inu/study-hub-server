package kr.co.studyhubinu.studyhubserver.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.studyhubinu.studyhubserver.config.WebConfig;
import kr.co.studyhubinu.studyhubserver.config.jwt.JwtResponseDto;
import kr.co.studyhubinu.studyhubserver.config.resolver.UserIdArgumentResolver;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNicknameDuplicateException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotAccessRightException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserId;
import kr.co.studyhubinu.studyhubserver.user.dto.request.*;
import kr.co.studyhubinu.studyhubserver.user.dto.response.GetUserResponse;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.service.UserService;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.*;
import static kr.co.studyhubinu.studyhubserver.user.enums.MajorType.COMPUTER_SCIENCE_ENGINEERING;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.*;
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

    @MockBean
    UserIdArgumentResolver userIdArgumentResolver;

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
        resultActions.andExpect(status().isOk())
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
    void 로그인_성공시_토큰_반환() throws Exception {
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


    @Test
    void 회원_단건조회_성공() throws Exception {
        // given
        when(userIdArgumentResolver.supportsParameter(any())).thenReturn(true);
        when(userIdArgumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn(new UserId(1L));
        when(userService.getUser(any())).thenReturn(GetUserResponse.builder()
                .email("dw@inu.ac.kr")
                .nickname("김동우동")
                .build());

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
        );
        MvcResult mvcResult = resultActions.andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString(UTF_8);

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print());
        assertTrue(responseBody.contains("김동우동"));
    }

    @Test
    void 닉네임_중복검사_중복X() throws Exception {
        // given
        doNothing().when(userService).nicknameDuplicationValid(any());

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/users/duplication-nickname")
                        .param("nickname", "dw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
        );

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void 닉네임_중복검사_중복O() throws Exception {
        // given
        willThrow(new UserNicknameDuplicateException()).given(userService).nicknameDuplicationValid(any());

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/users/duplication-nickname")
                        .param("nickname", "dw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
        );
        MvcResult mvcResult = resultActions.andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString(UTF_8);

        // then
        resultActions.andExpect(status().is4xxClientError())
                .andDo(print());
        assertTrue(responseBody.contains("이미 사용중인 닉네임 입니다"));
    }

    @Test
    void 닉네임_수정_성공() throws Exception {
        doNothing().when(userService).updateNickname(any());

        UpdateNicknameRequest updateNicknameRequest = UpdateNicknameRequest.builder()
                .nickname("studyDDD")
                .build();

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/users/nickname")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateNicknameRequest))
        );

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void 닉네임_수정_실패_10자_이상_닉네임() throws Exception {
        doNothing().when(userService).updateNickname(any());

        UpdateNicknameRequest updateNicknameRequest = UpdateNicknameRequest.builder()
                .nickname("helloImliljay")
                .build();

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/users/nickname")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateNicknameRequest))
        );
        MvcResult mvcResult = resultActions.andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString(UTF_8);

        // then
        resultActions.andExpect(status().is4xxClientError())
                .andDo(print());
        assertTrue(responseBody.contains("닉네임은 10자 이하여야 합니다"));
    }

    @Test
    void 비밀번호_수정_성공() throws Exception {
        doNothing().when(userService).updatePassword(any());

        UpdatePasswordRequest updatePasswordRequest = UpdatePasswordRequest.builder()
                .password("liljayjayjay@")
                .auth(true)
                .build();

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/users/password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatePasswordRequest))
        );

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("passwordParameters")
    void 비밀번호_수정_실패_잘못된형식(String testName, String password) throws Exception {
        doNothing().when(userService).updatePassword(any());

        UpdatePasswordRequest updatePasswordRequest = UpdatePasswordRequest.builder()
                .password(password)
                .auth(true)
                .build();

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/users/password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatePasswordRequest))
        );

        // then
        resultActions.andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    void 전공_수정_성공() throws Exception {
        doNothing().when(userService).updateMajor(any());

        UpdateMajorRequest updateMajorRequest = UpdateMajorRequest.builder()
                .major(COMPUTER_SCIENCE_ENGINEERING)
                .build();

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/users/major")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateMajorRequest))
        );

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void 비밀번호_중복_검증_중복X() throws Exception {
        doNothing().when(userService).verifyPassword(any(), any());

        VerifyPasswordRequest verifyPasswordRequest = VerifyPasswordRequest.builder()
                .password("liljayjayjay@")
                .build();

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/users/password/verify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(verifyPasswordRequest))
        );

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void 비밀번호_중복_검증_중복O() throws Exception {
        willThrow(new UserNotAccessRightException()).given(userService).verifyPassword(any(), any());

        VerifyPasswordRequest verifyPasswordRequest = VerifyPasswordRequest.builder()
                .password("liljayjayjay@")
                .build();

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/users/password/verify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(verifyPasswordRequest))
        );

        // then
        MvcResult mvcResult = resultActions.andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString(UTF_8);

        // then
        resultActions.andExpect(status().is4xxClientError())
                .andDo(print());
        assertTrue(responseBody.contains("접근권한이 없는 유저입니다"));
    }

    @Test
    void 회원_탈퇴_성공() throws Exception {
        // given
        doNothing().when(userService).deleteUser(any());

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/v1/users")
        );

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void 회원_탈퇴_실패() throws Exception {
        // given
        willThrow(new UserNotFoundException()).given(userService).deleteUser(any());

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/v1/users")
        );
        MvcResult mvcResult = resultActions.andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString(UTF_8);

        // then
        resultActions.andExpect(status().is4xxClientError())
                .andDo(print());
        assertTrue(responseBody.contains("해당 아이디를 가진 유저가 없습니다. 아이디 값을 다시 한번 확인하세요."));
    }
}