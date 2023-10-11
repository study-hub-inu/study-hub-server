package kr.co.studyhubinu.studyhubserver.studypost.controller;

import io.swagger.v3.oas.annotations.Operation;
import kr.co.studyhubinu.studyhubserver.studypost.dto.request.CreatePostRequest;
import kr.co.studyhubinu.studyhubserver.studypost.dto.request.UpdatePostRequest;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.GetBookmarkedPostsResponse;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.GetMyPostResponse;
import kr.co.studyhubinu.studyhubserver.studypost.service.StudyPostService;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/study-posts")
public class StudyPostController {

    private final StudyPostService studyPostService;

    @Operation(summary = "스터디 게시글 생성",
            description = "Http 헤더에 JWT accessToken 과 함께 " +
                    "제목, 내용, 채팅방 URI, 관련 학과, 스터디 정원, 벌금, 필터 성별, 스터디 방식, 스터디 시작 날짜, 스터디 종료 날짜를 Json 형식으로 입력해주세요.")
    @PostMapping("")
    public ResponseEntity<HttpStatus> createPost(@Valid @RequestBody CreatePostRequest request, UserId userId) {
        studyPostService.createPost(request.toService(userId.getId()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "스터디 게시글 수정", description = "Http 헤더에 JWT accessToken 과 함께 " +
            "게시글 Id, 제목, 내용, 채팅방 URI, 관련 학과, 스터디 정원, 벌금, 필터 성별, 스터디 방식, 스터디 시작 날짜, 스터디 종료 날짜를 Json 형식으로 입력해주세요.")
    @PutMapping("")
    public ResponseEntity<HttpStatus> updatePost(@Valid @RequestBody UpdatePostRequest request, UserId userId) {
        studyPostService.updatePost(request.toService(userId.getId()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "스터디 게시글 삭제",
            description = "Http 헤더에 JWT accessToken 과 함께 게시글 Id를 보내주시면 됩니다.")
    @DeleteMapping("/{postId}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable("postId") Long postId, UserId userId) {
        studyPostService.deletePost(postId, userId.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "내가 쓴 스터디 조회")
    @GetMapping("/mypost")
    public ResponseEntity<GetMyPostResponse> getMyPosts(@RequestParam int page, @RequestParam int size, UserId userId) {
        return ResponseEntity.ok(studyPostService.getMyPosts(page, size, userId.getId()));
    }

    @Operation(summary = "내가 북마크한 스터디 조회")
    @GetMapping("/bookmarked")
    public ResponseEntity<GetBookmarkedPostsResponse> getBookmarkedPosts(@RequestParam int page, @RequestParam int size, UserId userId) {
        return ResponseEntity.ok().body(studyPostService.getBookmarkedPosts(page, size, userId.getId()));
    }
}