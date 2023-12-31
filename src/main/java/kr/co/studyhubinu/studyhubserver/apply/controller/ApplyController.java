package kr.co.studyhubinu.studyhubserver.apply.controller;


import io.swagger.v3.oas.annotations.Operation;
import kr.co.studyhubinu.studyhubserver.apply.dto.request.EnrollApplyRequest;
import kr.co.studyhubinu.studyhubserver.apply.dto.request.UpdateApplyRequest;
import kr.co.studyhubinu.studyhubserver.apply.service.ApplyService;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserId;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApplyController {

    private final ApplyService applyService;

    @Operation(summary = "스터디 참여 신청", description = "studyId와 신청자가 작성한 글을 json 형태로 입력해주세요.")
    @PostMapping("/v1/study/enroll")
    public ResponseEntity<HttpStatus> enrollStudy(UserId userId, EnrollApplyRequest request) {
        applyService.enroll(userId.getId(), request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "스터디 참여 신청 정보 수정",
            description = "참여 신청한 유저의 Id, 해당 스터디 Id, 변경하려는 상태(ACCEPT, STANDBY, REJECT)를 보내주세요")
    @PostMapping("/v1/study/update")
    public ResponseEntity<HttpStatus> updateStudyInspection(UpdateApplyRequest request) {
        applyService.update(request);
        return ResponseEntity.ok().build();
    }
}
