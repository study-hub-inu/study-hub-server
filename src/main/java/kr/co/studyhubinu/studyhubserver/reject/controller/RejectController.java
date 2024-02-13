package kr.co.studyhubinu.studyhubserver.reject.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.v3.oas.annotations.Operation;
import kr.co.studyhubinu.studyhubserver.reject.dto.response.RejectReasonResponse;
import kr.co.studyhubinu.studyhubserver.reject.service.RejectService;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RejectController {

    private final RejectService rejectService;

    @Operation(summary = "거절 사유 조회", description = "JWT와 studyId를 보내주세요.")
    @ApiImplicitParam(name = "studyId", value = "스터디 식별자", required = true)
    @GetMapping("/v1/reject")
    public ResponseEntity<RejectReasonResponse> enrollStudy(UserId userId, @RequestParam Long studyId) {
        return ResponseEntity.ok(rejectService.findRejectReason(userId, studyId));
    }
}
