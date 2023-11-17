package kr.co.studyhubinu.studyhubserver.comment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.studyhubinu.studyhubserver.comment.domain.CommentEntity;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CreateCommentRequest {

    @Schema(description = "댓글을 달 게시글 id", example = "1")
    private Long postId;

    @Schema(description = "댓글 내용", example = "새내기도 지원 가능한가요?")
    @NotBlank(message = "댓글 내용은 필수 입니다")
    private String content;

    public CommentEntity toEntity(Long userId) {
        return CommentEntity.builder()
                .postId(postId)
                .userId(userId)
                .content(content)
                .build();
    }
}
