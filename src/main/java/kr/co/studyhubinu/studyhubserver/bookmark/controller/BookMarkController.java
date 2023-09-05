package kr.co.studyhubinu.studyhubserver.bookmark.controller;

import kr.co.studyhubinu.studyhubserver.bookmark.dto.request.CreateBookMarkRequest;
import kr.co.studyhubinu.studyhubserver.bookmark.dto.response.FindBookMarkResponse;
import kr.co.studyhubinu.studyhubserver.bookmark.service.BookMarkService;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookmark")
public class BookMarkController {

    private final BookMarkService bookMarkService;

    @GetMapping("")
    public ResponseEntity<Slice<FindBookMarkResponse>> findBookMark(UserId userId) {
        return ResponseEntity.ok(bookMarkService.findBookMark(userId.getId()));
    }

    @PostMapping("")
    public ResponseEntity<?> saveBookMark(UserId userId, CreateBookMarkRequest request) {
        bookMarkService.saveBookMark(userId.getId(), request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("{bookMarkId}")
    public ResponseEntity<?> deleteBookMark(@PathVariable("bookMarkId") Long bookMarkId) {
        bookMarkService.deleteBookMark(bookMarkId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
