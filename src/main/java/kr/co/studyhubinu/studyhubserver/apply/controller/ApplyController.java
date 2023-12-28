package kr.co.studyhubinu.studyhubserver.apply.controller;


import kr.co.studyhubinu.studyhubserver.apply.dto.request.ApproveUserStudyRequestDto;
import kr.co.studyhubinu.studyhubserver.apply.dto.request.RefuseUserStudyRequestDto;
import kr.co.studyhubinu.studyhubserver.apply.service.ApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApplyController {

    private final ApplyService applyService;

//    @PostMapping("/v1/study/approve")
//    public ResponseEntity<HttpStatus> approveUser(ApproveUserStudyRequestDto approveUserStudyRequestDto) {
//        applyService.approve(approveUserStudyRequestDto.getUserId(), approveUserStudyRequestDto.getPostId());
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @PostMapping("/v1/study/refuse")
//    public ResponseEntity<HttpStatus> refuseUser(RefuseUserStudyRequestDto refuseUserStudyRequestDto) {
//        applyService.refuse(refuseUserStudyRequestDto.getUserId(), refuseUserStudyRequestDto.getStudyId());
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
