package kr.co.studyhubinu.studyhubserver.notice.controller;

import io.swagger.v3.oas.annotations.Operation;
import kr.co.studyhubinu.studyhubserver.notice.dto.response.FindNoticeResponse;
import kr.co.studyhubinu.studyhubserver.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NoticeController {

    private final NoticeService noticeService;

    @Operation(summary = "공지사항 조회", description = "page, size만 보내주시면 됩니다!")
    @GetMapping("/v1/announce")
    public ResponseEntity<Slice<FindNoticeResponse>> getNotice(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok().body(noticeService.getNotice(page, size));
    }

}
