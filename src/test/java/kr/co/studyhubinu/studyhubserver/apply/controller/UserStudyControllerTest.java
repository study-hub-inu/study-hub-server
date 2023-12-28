//package kr.co.studyhubinu.studyhubserver.userstudy.controller;
//
//import kr.co.studyhubinu.studyhubserver.config.resolver.UserIdArgumentResolver;
//import kr.co.studyhubinu.studyhubserver.support.controller.ControllerRequest;
//import kr.co.studyhubinu.studyhubserver.userstudy.dto.request.ApproveUserStudyRequestDto;
//import kr.co.studyhubinu.studyhubserver.userstudy.service.UserStudyService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.doNothing;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(value = UserStudyController.class)
//class UserStudyControllerTest extends ControllerRequest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    UserStudyService userStudyService;
//
//    @MockBean
//    UserIdArgumentResolver userIdArgumentResolver;
//
//    @Test
//    void 스터디_참여_수락_성공() throws Exception {
//        // given
//        ApproveUserStudyRequestDto userStudyDto = ApproveUserStudyRequestDto.builder().userId(1L).postId(1L).build();
//        doNothing().when(userStudyService).approve(anyLong(), anyLong());
//
//        // when
//        ResultActions resultActions = performPostRequest("/api/v1/study/approve", userStudyDto);
//
//        // then
//        resultActions.andExpect(status().isOk())
//                .andDo(print());
//    }
//
//    @Test
//    void 스터디_참여_수락_실패_유저Id_누락() throws Exception {
//        // given
//        ApproveUserStudyRequestDto userStudyDto = ApproveUserStudyRequestDto.builder().userId(1L).postId(1L).build();
//
//        // when
//        ResultActions resultActions = performPostRequest("/api/v1/study/approve", userStudyDto);
//
//        // then
//        resultActions.andExpect(status().isOk())
//                .andDo(print());
//    }
//
//    @Test
//    void refuseUser() {
//    }
//}