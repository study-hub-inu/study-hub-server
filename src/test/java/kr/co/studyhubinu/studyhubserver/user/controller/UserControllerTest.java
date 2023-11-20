package kr.co.studyhubinu.studyhubserver.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.studyhubinu.studyhubserver.config.WebConfig;
import kr.co.studyhubinu.studyhubserver.config.jwt.JwtAuthenticationFilter;
import kr.co.studyhubinu.studyhubserver.config.jwt.JwtAuthorizationFilter;
import kr.co.studyhubinu.studyhubserver.config.jwt.JwtProvider;
import kr.co.studyhubinu.studyhubserver.user.dto.response.GetUserResponse;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import kr.co.studyhubinu.studyhubserver.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@Import(JwtProvider.class)
@TestPropertySource(locations = "classpath:application-test.yml")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtProvider jwtProvider;

    @MockBean
    private UserService userService;

//    @MockBean
//    private JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    @MockBean
//    private JwtAuthorizationFilter jwtAuthorizationFilter;

    protected MediaType contentType =
            new MediaType(MediaType.APPLICATION_JSON.getType(),
                    MediaType.APPLICATION_JSON.getSubtype(),
                    StandardCharsets.UTF_8);


//    @Test
//    @DisplayName("회원 단건 조회를 한다")
//    void getUserTest() throws Exception {
//        // given
//        GetUserResponse response = GetUserResponse.builder()
//                .postCount(3L)
//                .participateCount(4L)
//                .bookmarkCount(2L)
//                .nickname("상남자김동우")
//                .major(MajorType.CONSUMER_SCIENCE)
//                .gender(GenderType.MALE)
//                .email("dldudwo@gmail.com")
//                .imageUrl("imageUrl.com")
//                .build();
//
//        given(userService.getUser(anyLong()))
//                .willReturn(response);
//        Long userId = 1L;
//        String testToken = jwtProvider.accessTokenCreate(userId);
//
//        // when // then
//        mockMvc.perform(
//                        get("/api/users")
//                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + testToken) // 생성된 토큰을 헤더에 추가
////                                .content(objectMapper.writeValueAsString(request)) 요청이 없기 때문에
//                                .contentType(contentType)
//                )
//                .andDo(print()) // 요청에 대한 로그를 더 자세하게 확인 가능
//                .andExpect(status().isOk());
//
//    }

}
