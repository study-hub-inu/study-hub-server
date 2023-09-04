package kr.co.studyhubinu.studyhubserver.bookmark.controller;

import kr.co.studyhubinu.studyhubserver.bookmark.dto.request.CreateBookMarkRequest;
import kr.co.studyhubinu.studyhubserver.bookmark.dto.request.FindBookMarkRequest;
import kr.co.studyhubinu.studyhubserver.bookmark.dto.response.FindBookMarkResponse;
import kr.co.studyhubinu.studyhubserver.bookmark.service.BookMarkService;
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
    public ResponseEntity<Slice<FindBookMarkResponse>> findBookMark(FindBookMarkRequest request) {
        return ResponseEntity.ok(bookMarkService.findBookMark(request));
    }

    @PostMapping("")
    public ResponseEntity<?> saveBookMark(CreateBookMarkRequest request) {
        bookMarkService.saveBookMark(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("{bookMarkId}")
    public ResponseEntity<?> deleteBookMark(@PathVariable("bookMarkId") Long bookMarkId) {
        bookMarkService.deleteBookMark(bookMarkId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
