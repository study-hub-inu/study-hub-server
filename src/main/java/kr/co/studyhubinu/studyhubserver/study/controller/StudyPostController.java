package kr.co.studyhubinu.studyhubserver.study.controller;

import kr.co.studyhubinu.studyhubserver.study.dto.request.CreatePostRequest;
import kr.co.studyhubinu.studyhubserver.study.service.StudyPostService;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/study-posts")
public class StudyPostController {

    private final StudyPostService studyPostService;

    @PostMapping("")
    public ResponseEntity createPost(@Valid @RequestBody CreatePostRequest request, UserId userId) {

        studyPostService.createPost(request.toService(userId.getId()));

        return new ResponseEntity(HttpStatus.CREATED);
    }

}
