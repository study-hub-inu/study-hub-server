package kr.co.studyhubinu.studyhubserver.bookmark.controller;

import io.swagger.v3.oas.annotations.Operation;
import kr.co.studyhubinu.studyhubserver.bookmark.dto.response.GetBookmarkedResponse;
import kr.co.studyhubinu.studyhubserver.bookmark.dto.response.GetDoBookmarkResponse;
import kr.co.studyhubinu.studyhubserver.bookmark.service.BookmarkService;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @Operation(summary = "북마크 저장, 삭제", description = "북마크가 되어있으면 삭제, 북마크 안되어있으면 북마크 생성")
    @PostMapping("/v1/bookmark/{postId}")
    public ResponseEntity<GetDoBookmarkResponse> saveBookMark(final UserId userId, @PathVariable final Long postId) {
        final boolean created = bookmarkService.doBookMark(userId.getId(), postId);
        return ResponseEntity.ok().body(new GetDoBookmarkResponse(created));
    }

    @Operation(summary = "북마크 여부 조회", description = "파라미어테 postId, 헤더에 userId 보내주시면 됩니다.")
    @GetMapping("/v1/bookmark/{postId}")
    public ResponseEntity<GetBookmarkedResponse> checkBookmarked(@PathVariable final Long postId, final UserId userId) {
        final boolean result = bookmarkService.checkBookmarked(userId.getId(), postId);
        return ResponseEntity.ok().body(new GetBookmarkedResponse(result));
    }

    @Operation(summary = "북마크 전체 삭제", description = "헤더에 userId 보내주시면 됩니다.")
    @DeleteMapping("/v1/bookmark")
    public ResponseEntity<HttpStatus> deleteAllBookmark(final UserId userId) {
        bookmarkService.deleteAllBookmark(userId.getId());
        return ResponseEntity.noContent().build();
    }

}
