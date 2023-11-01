package kr.co.studyhubinu.studyhubserver.userstudy.controller;


import kr.co.studyhubinu.studyhubserver.userstudy.dto.request.ApproveUserStudyRequestDto;
import kr.co.studyhubinu.studyhubserver.userstudy.dto.request.RefuseUserStudyRequestDto;
import kr.co.studyhubinu.studyhubserver.userstudy.service.UserStudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/study")
public class UserStudyController {

    private final UserStudyService userStudyService;

    @PostMapping("/approve")
    public ResponseEntity<HttpStatus> approveUser(ApproveUserStudyRequestDto approveUserStudyRequestDto) {
        userStudyService.approve(approveUserStudyRequestDto.getUserId(), approveUserStudyRequestDto.getPostId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/refuse")
    public ResponseEntity<HttpStatus> refuseUser(RefuseUserStudyRequestDto refuseUserStudyRequestDto) {
        userStudyService.refuse(refuseUserStudyRequestDto.getUserId(), refuseUserStudyRequestDto.getStudyId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
