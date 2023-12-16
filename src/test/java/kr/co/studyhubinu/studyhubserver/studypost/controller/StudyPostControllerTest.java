package kr.co.studyhubinu.studyhubserver.studypost.controller;

import kr.co.studyhubinu.studyhubserver.config.WebConfig;
import kr.co.studyhubinu.studyhubserver.config.resolver.UserIdArgumentResolver;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.GetBookmarkedPostsData;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.PostData;
import kr.co.studyhubinu.studyhubserver.studypost.dto.request.CreatePostRequest;
import kr.co.studyhubinu.studyhubserver.studypost.dto.request.UpdatePostRequest;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseById;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.GetBookmarkedPostsResponse;
import kr.co.studyhubinu.studyhubserver.studypost.service.StudyPostFindService;
import kr.co.studyhubinu.studyhubserver.studypost.service.StudyPostService;
import kr.co.studyhubinu.studyhubserver.support.controller.ControllerRequest;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static kr.co.studyhubinu.studyhubserver.studypost.controller.CreatePostRequestFixture.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = StudyPostController.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebConfig.class)
})
class StudyPostControllerTest extends ControllerRequest {


    @MockBean
    StudyPostService studyPostService;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StudyPostFindService studyPostFindService;

    @MockBean
    UserIdArgumentResolver userIdArgumentResolver;

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
        ResultActions resultActions = performPostRequest("/api/v1/study-posts", createPostRequest);

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
        ResultActions resultActions = performPostRequest("/api/v1/study-posts", CreatePostRequestFixture.createPostRequest_생성(CORRECT_POST));

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
        ResultActions resultActions = performPutRequest("/api/v1/study-posts", updatePostRequest);

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void 스터디_게시글_삭제_성공() throws Exception {
        // given
        doNothing().when(studyPostService).deletePost(any(), any());

        // when, then
        ResultActions resultActions = performDeleteRequest("/api/v1/study-posts/1");

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void 스터디_게시글_삭제_실패() throws Exception {
        // given
        doNothing().when(studyPostService).deletePost(any(), any());

        // when, then
        ResultActions resultActions = performDeleteRequest("/api/v1/study-post");

        // then
        resultActions.andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    void 북마크한_스터디_조회_성공() throws Exception {
        // given
        Slice<GetBookmarkedPostsData> data = new SliceImpl<>(new ArrayList<>(), PageRequest.of(1, 3), false);
        when(studyPostFindService.getBookmarkedPosts(anyInt(), anyInt(), any())).
                thenReturn(new GetBookmarkedPostsResponse(1L, data));

        Map<String, String> params = new HashMap<>();
        params.put("page", "0");
        params.put("size", "3");

        // when, then
        ResultActions resultActions = performGetRequest("/api/v1/study-posts/bookmarked", params);
        MvcResult mvcResult = resultActions.andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString(UTF_8);

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print());

        Assertions.assertThat(responseBody).contains("\"totalCount\":1");
        Assertions.assertThat(responseBody).contains("getBookmarkedPostsData");
    }

    @Test
    void 북마크한_스터디_조회_실패() throws Exception {
        // given
        when(studyPostFindService.getBookmarkedPosts(anyInt(), anyInt(), any())).thenReturn(null);
        Map<String, String> params = new HashMap<>();
        params.put("page", "0");

        // when, then
        ResultActions resultActions = performGetRequest("/api/v1/study-posts/bookmarked", params);

        // then
        resultActions.andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    void 스터디_단건_조회_성공() throws Exception {
        // given
        PostData postData = PostData.builder().build();
        when(studyPostFindService.findPostById(any(), any())).thenReturn(new FindPostResponseById(postData, new ArrayList<>()));

        // when, then
        ResultActions resultActions = performGetRequest("/api/v1/study-posts/1", null);
        MvcResult mvcResult = resultActions.andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString(UTF_8);

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print());
        Assertions.assertThat(responseBody).contains("postId", "title", "createdDate", "content", "major", "studyPerson", "filteredGender", "studyWay", "penalty");
    }
}