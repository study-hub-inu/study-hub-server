package kr.co.studyhubinu.studyhubserver.comment.controller;

import kr.co.studyhubinu.studyhubserver.bookmark.controller.CreateCommentRequestFixture;
import kr.co.studyhubinu.studyhubserver.bookmark.controller.UpdateCommentRequestFixture;
import kr.co.studyhubinu.studyhubserver.comment.dto.request.CreateCommentRequest;
import kr.co.studyhubinu.studyhubserver.comment.dto.request.UpdateCommentRequest;
import kr.co.studyhubinu.studyhubserver.comment.dto.response.CommentResponse;
import kr.co.studyhubinu.studyhubserver.comment.service.CommentService;
import kr.co.studyhubinu.studyhubserver.config.WebConfig;
import kr.co.studyhubinu.studyhubserver.config.resolver.UserIdArgumentResolver;
import kr.co.studyhubinu.studyhubserver.support.controller.ControllerRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.SliceImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static kr.co.studyhubinu.studyhubserver.bookmark.controller.CreateCommentRequestFixture.CORRECT_CREATE_COMMENT;
import static kr.co.studyhubinu.studyhubserver.bookmark.controller.UpdateCommentRequestFixture.CORRECT_COMMENT;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = CommentController.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebConfig.class)
})
class CommentControllerTest extends ControllerRequest {

    @MockBean
    CommentService commentService;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserIdArgumentResolver userIdArgumentResolver;

    @Test
    void 댓글_작성() throws Exception {
        // given
        doNothing().when(commentService).createComment(any(), anyLong());
        CreateCommentRequest createCommentRequest = CreateCommentRequestFixture.createCommentRequest_생성(CORRECT_CREATE_COMMENT);

        // when
        ResultActions resultActions = performPostRequest("/api/v1/comments", createCommentRequest);

        // then
        resultActions.andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void 댓글_수정() throws Exception {
        // given
        doNothing().when(commentService).updateComment(any(), anyLong());
        UpdateCommentRequest updateCommentRequest = UpdateCommentRequestFixture.updateCommentRequest_생성(CORRECT_COMMENT);

        // when
        ResultActions resultActions = performPutRequest("/api/v1/comments", updateCommentRequest);

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void 댓글_삭제() throws Exception {
        // given
        doNothing().when(commentService).deleteComment(anyLong(), anyLong());

        // when
        ResultActions resultActions = performDeleteRequest("/api/v1/comments/1");

        // then
        resultActions.andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    public void 댓글_리스트_조회() throws Exception {

        // given
        SliceImpl<CommentResponse> comment = new SliceImpl<>(new ArrayList<>(), PageRequest.of(1, 3), false);
        Map<String, String> params = new HashMap<>();
        params.put("page", "0");
        params.put("size", "3");
        when(commentService.getComments(anyLong(),anyInt(), anyInt(), any())).thenReturn(comment);

        // when
        ResultActions resultActions = performGetRequest("/api/v1/comments/1", params);
        MvcResult mvcResult = resultActions.andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString(UTF_8);

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print());

        Assertions.assertThat(responseBody).contains("\"pageNumber\":1");
        Assertions.assertThat(responseBody).contains("\"pageSize\":3");
    }
}
