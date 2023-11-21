package kr.co.studyhubinu.studyhubserver.studypost.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import kr.co.studyhubinu.studyhubserver.studypost.dto.request.InquiryRequest;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.*;
import kr.co.studyhubinu.studyhubserver.studypost.service.StudyPostService;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/study-posts/find")
public class StudyPostFindController {

    private final StudyPostService studyPostService;

    @Operation(summary = "스터디 단건 조회", description = "url 끝에 postId를 넣어주세요")
    @GetMapping("/{postId}")
    public ResponseEntity<FindPostResponseById> findPostById(@PathVariable Long postId, UserId userId) {
        return ResponseEntity.ok(studyPostService.findPostById(postId, userId.getId()));
    }

    @Operation(summary = "내가 쓴 스터디 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "페이지", required = true),
            @ApiImplicitParam(name = "size", value = "사이즈", required = true)
    })
    @GetMapping("/mypost")
    public ResponseEntity<GetMyPostResponse> getMyPosts(@RequestParam int page, @RequestParam int size, UserId userId) {
        return ResponseEntity.ok(studyPostService.getMyPosts(page, size, userId.getId()));
    }

    @Operation(summary = "스터디 게시글 전체 조회", description = "parameter 칸에 조회할 내용을 입력해주세요")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "inquiry", value = "제목"),
            @ApiImplicitParam(name = "page", value = "페이지", required = true),
            @ApiImplicitParam(name = "size", value = "사이즈", required = true)
    })
    @GetMapping("")
    public ResponseEntity<Slice<FindPostResponseByInquiry>> findPostByAllString(final InquiryRequest inquiryRequest, @RequestParam int page, @RequestParam int size, UserId userId) {
        return ResponseEntity.ok(studyPostService.findPostResponseByInquiry(inquiryRequest, page, size, userId.getId()));
    }

    @Operation(summary = "스터디 게시글 인기순, 제목, 내용 조회", description = "parameter 칸에 " +
        "페이지 정보는 page, 조회할 행 개수는 size 에 입력해주세요")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "페이지", required = true),
            @ApiImplicitParam(name = "size", value = "사이즈", required = true)
    })
    @GetMapping("/hot")
    public ResponseEntity<Slice<FindPostResponseByRemainingSeat>> findPostByBookMark(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(studyPostService.findPostResponseByBookMark(pageable));
    }

}
