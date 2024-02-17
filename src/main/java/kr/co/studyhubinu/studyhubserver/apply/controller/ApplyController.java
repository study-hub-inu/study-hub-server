package kr.co.studyhubinu.studyhubserver.apply.controller;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import kr.co.studyhubinu.studyhubserver.apply.dto.request.*;
import kr.co.studyhubinu.studyhubserver.apply.dto.response.FindApplyResponse;
import kr.co.studyhubinu.studyhubserver.apply.dto.response.FindMyRequestApplyResponse;
import kr.co.studyhubinu.studyhubserver.apply.enums.Inspection;
import kr.co.studyhubinu.studyhubserver.apply.service.ApplyService;
import kr.co.studyhubinu.studyhubserver.apply.dto.response.FindParticipateApplyResponse;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserId;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApplyController {

    private final ApplyService applyService;

    @Operation(summary = "스터디 참여 신청", description = "studyId와 신청자가 작성한 글을 json 형태로 입력해주세요.")
    @PostMapping("/v1/study")
    public ResponseEntity<HttpStatus> enrollStudy(UserId userId, EnrollApplyRequest request) {
        applyService.enroll(userId, request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "스터디 참여 신청 거절",
            description = "JWT 헤더에 보내주시면 됩니다!")
    @PutMapping("/v1/study-reject")
    public ResponseEntity<HttpStatus> rejectApply(@RequestBody @Valid final RejectApplyRequest rejectApplyRequest, final UserId userId) {
        applyService.rejectApply(rejectApplyRequest, userId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "스터디 참여 신청 수락",
            description = "JWT 헤더에 보내주시면 됩니다!")
    @PutMapping("/v1/study-accept")
    public ResponseEntity<HttpStatus> acceptApply(@RequestBody @Valid final AcceptApplyRequest acceptApplyRequest, final UserId userId) {
        applyService.acceptApply(acceptApplyRequest, userId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "스터디 참여 신청 정보 조회", description = "해당 스터디 Id, 신청 정보를 파라미터로 보내주세요.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "페이지", required = true),
            @ApiImplicitParam(name = "size", value = "사이즈", required = true),
            @ApiImplicitParam(name = "studyId", value = "스터디 식별자", required = true),
            @ApiImplicitParam(name = "inspection", value = "상태", required = true)
    })
    @GetMapping("/v2/study")
    public FindApplyResponse findStudyEnroll(@RequestParam Long studyId, @RequestParam Inspection inspection, @RequestParam int page, @RequestParam int size) {
        return applyService.findApply(new FindApplyRequest(studyId, inspection), page, size);
    }

    @Operation(summary = "내가 참여한 스터디 목록", description = "헤더에 JWT 보내주시면 됩니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "페이지", required = true),
            @ApiImplicitParam(name = "size", value = "사이즈", required = true)
    })
    @GetMapping("/v1/participated-study")
    public ResponseEntity<FindParticipateApplyResponse> getParticipateApply(UserId userId, @RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok().body(applyService.getParticipateApply(userId, page, size));
    }

    @Operation(summary = "내가 신청한 스터디 목록", description = "헤더에 JWT 보내주시면 됩니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "페이지", required = true),
            @ApiImplicitParam(name = "size", value = "사이즈", required = true)
    })
    @GetMapping("/v1/study-request")
    public ResponseEntity<FindMyRequestApplyResponse> getMyRequestApply(UserId userId, @RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(applyService.getMyRequestApply(userId, page, size));
    }

    @Operation(summary = "내가 신청한 스터디 삭제", description = "헤더에 JWT와 함께 studyId를 보내주시면 됩니다.")
    @ApiImplicitParam(name = "studyId", value = "스터디 식별자", required = true)
    @DeleteMapping("/v1/study")
    public ResponseEntity<HttpStatus> deleteApply(UserId userId, Long studyId) {
        applyService.deleteMyStudy(userId, studyId);
        return ResponseEntity.ok().build();
    }

}
