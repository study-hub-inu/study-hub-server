package kr.co.studyhubinu.studyhubserver.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.studyhubinu.studyhubserver.comment.dto.request.CreateCommentRequest;
import kr.co.studyhubinu.studyhubserver.comment.dto.request.UpdateCommentRequest;
import kr.co.studyhubinu.studyhubserver.comment.dto.response.CommentResponse;
import kr.co.studyhubinu.studyhubserver.comment.service.CommentService;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "comment", description = "comment api")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 작성")
    @PostMapping("/v1/comments")
    public ResponseEntity<HttpStatus> createComment(@Valid @RequestBody final CreateCommentRequest request, final UserId userId) {
        commentService.createComment(request, userId.getId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "댓글 수정")
    @PutMapping("/v1/comments")
    public ResponseEntity<HttpStatus> updateComment(@Valid @RequestBody final UpdateCommentRequest request, final UserId userId) {
        commentService.updateComment(request, userId.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/v1/comments/{comment-id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("comment-id") final Long commentId, final UserId userId) {
        commentService.deleteComment(commentId, userId.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "댓글 리스트 조회")
    @GetMapping("/v1/comments/{post-id}")
    public ResponseEntity<Slice<CommentResponse>> getComments(@PathVariable("post-id") final Long postId, @RequestParam final int page, @RequestParam final int size, final UserId userId) {
        final Slice<CommentResponse> commentResponses = commentService.getComments(postId, page, size, userId.getId());
        return ResponseEntity.ok().body(commentResponses);
    }

    @Operation(summary = "댓글 미리 보기")
    @GetMapping("/v1/comments/{post-id}/preview")
    public ResponseEntity<List<CommentResponse>> getPreviewComments(@PathVariable("post-id") final Long postId, final UserId userId) {
        final List<CommentResponse> commentPreviewResponse = commentService.getPreviewComments(postId, userId.getId());
        return ResponseEntity.ok().body(commentPreviewResponse);
    }


}
