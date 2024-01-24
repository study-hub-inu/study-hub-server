package kr.co.studyhubinu.studyhubserver.apply.controller;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import kr.co.studyhubinu.studyhubserver.apply.dto.request.EnrollApplyRequest;
import kr.co.studyhubinu.studyhubserver.apply.dto.request.FindApplyRequest;
import kr.co.studyhubinu.studyhubserver.apply.dto.request.UpdateApplyRequest;
import kr.co.studyhubinu.studyhubserver.apply.dto.response.FindApplyResponse;
import kr.co.studyhubinu.studyhubserver.apply.service.ApplyService;
import kr.co.studyhubinu.studyhubserver.apply.dto.response.FindParticipateApplyResponse;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserId;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApplyController {

    private final ApplyService applyService;

    @Operation(summary = "스터디 참여 신청", description = "studyId와 신청자가 작성한 글을 json 형태로 입력해주세요.")
    @PostMapping("/v1/study")
    public ResponseEntity<HttpStatus> enrollStudy(UserId userId, EnrollApplyRequest request) {
        applyService.enroll(userId.getId(), request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "스터디 참여 신청 정보 수정",
            description = "참여 신청한 유저의 Id, 해당 스터디 Id, 변경하려는 상태(ACCEPT, STANDBY, REJECT)를 보내주세요")
    @PutMapping("/v1/study")
    public ResponseEntity<HttpStatus> updateStudyInspection(UpdateApplyRequest request) {
        applyService.update(request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "스터디 참여 신청 정보 조회", description = "해당 스터디 Id를 보내주세요.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "페이지", required = true),
            @ApiImplicitParam(name = "size", value = "사이즈", required = true)
    })
    @GetMapping("/v1/study")
    public FindApplyResponse findStudyEnroll(FindApplyRequest request, @RequestParam int page, @RequestParam int size) {
        return applyService.findApply(request, page, size);
    }

    @Operation(summary = "내가 참여한 스터디 목록", description = "헤더에 JWT토큰 보내주시면 됩니다")
    @GetMapping("/v1/participated-study")
    public ResponseEntity<FindParticipateApplyResponse> getParticipateApply(UserId userId, @RequestParam int size, @RequestParam int page) {
        return ResponseEntity.ok().body(applyService.getParticipateApply(userId.getId(), size, page));
    }

}
