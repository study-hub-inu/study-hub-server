package kr.co.studyhubinu.studyhubserver.bookmark.controller;

import kr.co.studyhubinu.studyhubserver.bookmark.service.BookmarkService;
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

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(value = BookmarkController.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebConfig.class)
})
class BookmarkControllerTest extends ControllerRequest {

    @MockBean
    BookmarkService bookmarkService;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserIdArgumentResolver userIdArgumentResolver;

    @Test
    void 북마크_저장_삭제_성공() throws Exception {
        // given
        given(bookmarkService.doBookMark(anyLong(), anyLong())).willReturn(true);

        // when
        ResultActions resultActions = performPostRequest("/api/v1/bookmark/1");

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }
}
