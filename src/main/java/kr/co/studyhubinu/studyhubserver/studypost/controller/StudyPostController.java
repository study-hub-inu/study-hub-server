package kr.co.studyhubinu.studyhubserver.studypost.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import kr.co.studyhubinu.studyhubserver.config.resolver.QueryStringArgResolver;
import kr.co.studyhubinu.studyhubserver.studypost.dto.request.CreatePostRequest;
import kr.co.studyhubinu.studyhubserver.studypost.dto.request.InquiryRequest;
import kr.co.studyhubinu.studyhubserver.studypost.dto.request.UpdatePostRequest;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseById;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseByInquiry;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseByBookmark;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseByUserId;
import kr.co.studyhubinu.studyhubserver.studypost.service.StudyPostFindService;
import kr.co.studyhubinu.studyhubserver.studypost.service.StudyPostService;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StudyPostController {

    private final StudyPostService studyPostService;
    private final StudyPostFindService studyPostFindService;

    @Operation(summary = "스터디 게시글 생성",
            description = "Http 헤더에 JWT accessToken 과 함께 " +
                    "제목, 내용, 채팅방 URI, 관련 학과, 스터디 정원, 벌금, 필터 성별, 스터디 방식, 스터디 시작 날짜, 스터디 종료 날짜를 Json 형식으로 입력해주세요.")
    @PostMapping("/v1/study-posts")
    public ResponseEntity<Long> createPost(@Valid @RequestBody CreatePostRequest request, UserId userId) {
        Long postId = studyPostService.createPost(request.toService(userId.getId()));
        return ResponseEntity.ok(postId);
    }

    @Operation(summary = "스터디 게시글 수정", description = "Http 헤더에 JWT accessToken 과 함께 " +
            "게시글 Id, 제목, 내용, 채팅방 URI, 관련 학과, 스터디 정원, 벌금, 필터 성별, 스터디 방식, 스터디 시작 날짜, 스터디 종료 날짜를 Json 형식으로 입력해주세요.")
    @PutMapping("/v1/study-posts")
    public ResponseEntity<Long> updatePost(@Valid @RequestBody UpdatePostRequest request, UserId userId) {
        Long postId = studyPostService.updatePost(request.toService(userId.getId()));
        return ResponseEntity.ok(postId);
    }

    @Operation(summary = "스터디 게시글 삭제", description = "Http 헤더에 JWT accessToken 과 함께 게시글 Id를 보내주시면 됩니다.")
    @DeleteMapping("/v1/study-posts/{postId}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable("postId") Long postId, UserId userId) {
        studyPostService.deletePost(postId, userId.getId());
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "내가 북마크한 스터디 조회")
    @GetMapping("/v1/study-posts/bookmarked")
    public ResponseEntity<FindPostResponseByBookmark> getBookmarkedPosts(@RequestParam int page, @RequestParam int size, UserId userId) {
        FindPostResponseByBookmark findPostResponseByBookmark = studyPostFindService.getBookmarkedPosts(page, size, userId.getId());
        return ResponseEntity.ok().body(findPostResponseByBookmark);
    }

    @Operation(summary = "스터디 단건 조회", description = "url 끝에 postId를 넣어주세요")
    @GetMapping("/v1/study-posts/{postId}")
    public ResponseEntity<FindPostResponseById> findPostById(@PathVariable Long postId, UserId userId) {
        FindPostResponseById findPostResponseById = studyPostFindService.findPostById(postId, userId.getId());
        return ResponseEntity.ok(findPostResponseById);
    }

    @Operation(summary = "내가 작성한 스터디 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "페이지", required = true),
            @ApiImplicitParam(name = "size", value = "사이즈", required = true)
    })
    @GetMapping("/v1/study-posts/mypost")
    public ResponseEntity<FindPostResponseByUserId> getMyPosts(@RequestParam int page, @RequestParam int size, UserId userId) {
        FindPostResponseByUserId findPostResponseByUserId = studyPostFindService.getMyPosts(page, size, userId.getId());
        return ResponseEntity.ok(findPostResponseByUserId);
    }

    @Operation(summary = "스터디 게시글 전체 조회", description = "parameter 칸에 조회할 내용을 입력해주세요")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "inquiry", value = "검색 값"),
            @ApiImplicitParam(name = "titleAndMajor", value = "true = 제목, 학과 중 하나만 일치할 경우, false = 학과만 일치"),
            @ApiImplicitParam(name = "hot", value = "true = 인기순 O, false = 인기순 X"),
            @ApiImplicitParam(name = "page", value = "페이지", required = true),
            @ApiImplicitParam(name = "size", value = "사이즈", required = true)
    })
    @GetMapping("/v1/study-posts")
    public ResponseEntity<FindPostResponseByInquiry> findPostByAllString(@QueryStringArgResolver InquiryRequest inquiryRequest, @RequestParam int page, @RequestParam int size, UserId userId) {
        FindPostResponseByInquiry findPostResponseByInquiries = studyPostFindService.findPostResponseByInquiry(inquiryRequest, page, size, userId.getId());
        return ResponseEntity.ok(findPostResponseByInquiries);
    }

    @PutMapping("/v1/study-posts/{post-id}/close")
    public ResponseEntity<HttpStatus> closePost(@PathVariable("post-id") Long postId, UserId userId) {
        studyPostService.closePost(postId, userId.getId());
        return ResponseEntity.ok().build();
    }
}
