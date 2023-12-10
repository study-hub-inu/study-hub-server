package kr.co.studyhubinu.studyhubserver.studypost.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.studyhubinu.studyhubserver.config.WebConfig;
import kr.co.studyhubinu.studyhubserver.config.resolver.UserIdArgumentResolver;
import kr.co.studyhubinu.studyhubserver.studypost.dto.request.CreatePostRequest;
import kr.co.studyhubinu.studyhubserver.studypost.dto.request.UpdatePostRequest;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.GetBookmarkedPostsResponse;
import kr.co.studyhubinu.studyhubserver.studypost.service.StudyPostFindService;
import kr.co.studyhubinu.studyhubserver.studypost.service.StudyPostService;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static kr.co.studyhubinu.studyhubserver.studypost.controller.CreatePostRequestFixture.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = StudyPostController.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebConfig.class)
})
class StudyPostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StudyPostService studyPostService;

    @MockBean
    StudyPostFindService studyPostFindService;

    @MockBean
    UserIdArgumentResolver userIdArgumentResolver;

    ObjectMapper objectMapper = new ObjectMapper();

    static Stream<Arguments> requestParameters() {
        return Stream.of(
                Arguments.of("제목 X", CreatePostRequestFixture.createPostRequest_생성(BLANK_TITLE), "제목 작성은 필수입니다!"),
                Arguments.of("내용 X", CreatePostRequestFixture.createPostRequest_생성(BLANK_CONTENT), "내용 작성은 필수입니다!"),
                Arguments.of("채팅방 url X", CreatePostRequestFixture.createPostRequest_생성(BLANK_URL), "채팅방 url 작성은 필수입니다!"),
                Arguments.of("관련 학과 X", CreatePostRequestFixture.createPostRequest_생성(NULL_MAJOR), "관련 학과 작성은 필수입니다!"),
                Arguments.of("스터디 정원 1", CreatePostRequestFixture.createPostRequest_생성(MIN_STUDY_PERSON), "스터디 정원은 2명 이상입니다!"),
                Arguments.of("성별 X", CreatePostRequestFixture.createPostRequest_생성(NULL_GENDER), "허용 성별 작성은 필수입니다!"),
                Arguments.of("스터디 시작 날짜 X", CreatePostRequestFixture.createPostRequest_생성(NULL_START), "스터디 시작 날짜 작성은 필수입니다!"),
                Arguments.of("스터디 종료 날짜 X", CreatePostRequestFixture.createPostRequest_생성(NULL_END), "스터디 종료 날짜 작성은 필수입니다!")
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("requestParameters")
    void 스터디_게시글_생성_실패(String testName, CreatePostRequest createPostRequest, String msg) throws Exception {
        // given
        when(studyPostService.createPost(any())).thenReturn(1L);

        // when, then
        ResultActions resultActions = mockMvc.perform(
                post("/api/v1/study-posts")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createPostRequest))
        );

        MvcResult mvcResult = resultActions.andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString(UTF_8);

        // then
        resultActions.andExpect(status().is4xxClientError())
                .andDo(print());
        assertTrue(responseBody.contains(msg));
    }

    @Test
    void 스터디_게시글_생성_성공() throws Exception {
        // given
        when(studyPostService.createPost(any())).thenReturn(1L);

        // when, then
        ResultActions resultActions = mockMvc.perform(
                post("/api/v1/study-posts")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CreatePostRequestFixture.createPostRequest_생성(CORRECT_POST)))
        );

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void 스터디_게시글_수정_성공() throws Exception {
        // given
        when(studyPostService.updatePost(any())).thenReturn(1L);
        UpdatePostRequest updatePostRequest = CreatePostRequestFixture.updatePostRequest_생성(CORRECT_POST);

        // when, then
        ResultActions resultActions = mockMvc.perform(
                put("/api/v1/study-posts")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatePostRequest))
        );

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }



    @Test
    void 북마크한_스터디_조회_실패() {
    }

    @Test
    void findPostByAllString() {
    }
}