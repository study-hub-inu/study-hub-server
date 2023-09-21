package kr.co.studyhubinu.studyhubserver.bookmark.controller;

import io.swagger.v3.oas.annotations.Operation;
import kr.co.studyhubinu.studyhubserver.bookmark.dto.request.CreateBookMarkRequest;
import kr.co.studyhubinu.studyhubserver.bookmark.service.BookMarkService;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookmark")
public class BookMarkController {

    private final BookMarkService bookMarkService;

    @Operation(summary = "북마크 저장", description = "Http 헤더에 JWT accessToken, 바디에 PostId를 Json 형식으로 보내주시면 됩니다.")
    @PostMapping("")
    public ResponseEntity<HttpStatus> saveBookMark(UserId userId, CreateBookMarkRequest request) {
        bookMarkService.saveBookMark(userId.getId(), request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "북마크 삭제", description = "바디에 BookMarkId 를 Json 형식으로 보내주시면 됩니다.")
    @DeleteMapping("/{bookMarkId}")
    public ResponseEntity<HttpStatus> deleteBookMark(@PathVariable("bookMarkId") Long bookMarkId) {
        bookMarkService.deleteBookMark(bookMarkId);
        return ResponseEntity.noContent().build();
    }

}
