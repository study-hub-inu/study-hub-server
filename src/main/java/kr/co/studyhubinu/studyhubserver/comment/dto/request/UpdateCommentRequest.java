package kr.co.studyhubinu.studyhubserver.comment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UpdateCommentRequest {

    @Schema(description = "수정할 comment id")
    private Long commentId;

    @Schema(description = "수정할 댓글 내용", example = "휴학생도 지원 가능한가요?")
    @NotBlank(message = "댓글 내용은 필수 입니다")
    private String content;
}
