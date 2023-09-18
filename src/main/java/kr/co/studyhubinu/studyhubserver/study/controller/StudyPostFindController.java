package kr.co.studyhubinu.studyhubserver.study.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import kr.co.studyhubinu.studyhubserver.study.dto.response.FindPostResponseByAll;
import kr.co.studyhubinu.studyhubserver.study.dto.response.FindPostResponseByContent;
import kr.co.studyhubinu.studyhubserver.study.dto.response.FindPostResponseByMajor;
import kr.co.studyhubinu.studyhubserver.study.dto.response.FindPostResponseByString;
import kr.co.studyhubinu.studyhubserver.study.service.StudyPostService;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
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

    @Operation(summary = "스터디 게시글 전체 조회", description = "parameter 칸에 " +
            "페이지 정보는 page, 조회할 행 개수는 size 에 입력해주세요")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "페이지", required = true),
            @ApiImplicitParam(name = "size", value = "사이즈", required = true)
    })
    @GetMapping("")
    public ResponseEntity<Slice<FindPostResponseByAll>> findPostAll(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(studyPostService.findPostResponseByAll(pageable));
    }

    @Operation(summary = "스터디 게시글 제목으로 조회", description = "parameter 칸에 " +
            "조회할 제목은 detail, 페이지 정보는 page, 조회할 행 개수는 size 에 입력해주세요")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "내용", required = true),
            @ApiImplicitParam(name = "page", value = "페이지", required = true),
            @ApiImplicitParam(name = "size", value = "사이즈", required = true)
    })
    @GetMapping("/title/{title}")
    public ResponseEntity<Slice<FindPostResponseByString>> findPostByString(@PathVariable String title, @RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(studyPostService.findPostResponseByTitle(title, pageable));
    }

    @Operation(summary = "스터디 게시글 학과로 조회", description = " parameter 칸에 " +
            "조회할 학과는 major, 페이지 정보는 page, 조회할 행 개수는 size 에 입력해주세요")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "major", value = "학과", required = true),
            @ApiImplicitParam(name = "page", value = "페이지", required = true),
            @ApiImplicitParam(name = "size", value = "사이즈", required = true)
    })
    @GetMapping("/major/{major}")
    public ResponseEntity<Slice<FindPostResponseByMajor>> findPostByMajor(@PathVariable MajorType major, @RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(studyPostService.findPostResponseByMajor(major, pageable));
    }

    @Operation(summary = "스터디 게시글 내용으로 조회", description = " parameter 칸에 " +
            "조회할 내용은 content, 페이지 정보는 page, 조회할 행 개수는 size 에 입력해주세요")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", value = "내용", required = true),
            @ApiImplicitParam(name = "page", value = "페이지", required = true),
            @ApiImplicitParam(name = "size", value = "사이즈", required = true)
    })
    @GetMapping("/content/{content}")
    public ResponseEntity<Slice<FindPostResponseByContent>> findPostByContent(@PathVariable String content, @RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(studyPostService.findPostResponseByContent(content, pageable));
    }
}
