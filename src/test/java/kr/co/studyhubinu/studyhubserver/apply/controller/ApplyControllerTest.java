package kr.co.studyhubinu.studyhubserver.apply.controller;

import kr.co.studyhubinu.studyhubserver.apply.dto.request.EnrollApplyRequest;
import kr.co.studyhubinu.studyhubserver.apply.dto.request.FindApplyRequest;
import kr.co.studyhubinu.studyhubserver.apply.dto.request.UpdateApplyRequest;
import kr.co.studyhubinu.studyhubserver.apply.dto.response.FindApplyResponse;
import kr.co.studyhubinu.studyhubserver.apply.enums.Inspection;
import kr.co.studyhubinu.studyhubserver.apply.service.ApplyService;
import kr.co.studyhubinu.studyhubserver.config.resolver.UserIdArgumentResolver;
import kr.co.studyhubinu.studyhubserver.support.controller.ControllerRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static reactor.core.publisher.Mono.when;

@WebMvcTest(value = ApplyController.class)
class ApplyControllerTest extends ControllerRequest {

    @MockBean
    ApplyService applyService;

    @MockBean
    UserIdArgumentResolver userIdArgumentResolver;

    @Autowired
    MockMvc mockMvc;

    @Test
    void 스터디_가입_요청_성공() throws Exception {
        // given
        doNothing().when(applyService).enroll(any(), any());
        EnrollApplyRequest request = EnrollApplyRequest.builder()
                .studyId(1L)
                .build();

        // when
        ResultActions resultActions = performPostRequest("/api/v1/study", request);

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void 스터디_요청상태_조회() throws Exception {
        // given
        FindApplyResponse findApplyResponse = FindApplyResponse.builder().build();

        Mockito.when(applyService.findApply(any(), anyInt(), anyInt())).thenReturn(findApplyResponse);
        Map<String, String> params = new HashMap<>();
        params.put("studyId", "1");
        params.put("inspection", "ACCEPT");
        params.put("page", "0");
        params.put("size", "5");

        // when
        ResultActions resultActions = performGetRequest("/api/v2/study", params);

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void 스터디_요청_삭제() throws Exception {
        // given
        doNothing().when(applyService).deleteMyStudy(any(), anyLong());

        // when
        ResultActions resultActions = performDeleteRequest("/api/v1/study/1");

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }
}