package kr.co.studyhubinu.studyhubserver.comment.controller;

import kr.co.studyhubinu.studyhubserver.bookmark.controller.CreateCommentRequestFixture;
import kr.co.studyhubinu.studyhubserver.bookmark.controller.UpdateCommentRequestFixture;
import kr.co.studyhubinu.studyhubserver.comment.dto.request.CreateCommentRequest;
import kr.co.studyhubinu.studyhubserver.comment.dto.request.UpdateCommentRequest;
import kr.co.studyhubinu.studyhubserver.comment.service.CommentService;
import kr.co.studyhubinu.studyhubserver.config.WebConfig;
import kr.co.studyhubinu.studyhubserver.config.resolver.UserIdArgumentResolver;
import kr.co.studyhubinu.studyhubserver.support.controller.ControllerRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static kr.co.studyhubinu.studyhubserver.bookmark.controller.CreateCommentRequestFixture.CORRECT_CREATE_COMMENT;
import static kr.co.studyhubinu.studyhubserver.bookmark.controller.UpdateCommentRequestFixture.CORRECT_COMMENT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
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

}
