package kr.co.studyhubinu.studyhubserver.bookmark.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.studyhubinu.studyhubserver.bookmark.domain.BookMarkEntity;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class FindBookMarkRequest {

    @Schema(description = "유저 id", example = "1")
    @NotBlank
    private Long userId;

    @Schema(description = "게시글 id", example = "1")
    @NotBlank
    private Long postId;

    public FindBookMarkRequest(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }

    public BookMarkEntity toEntity(FindBookMarkRequest request) {
        return BookMarkEntity.builder()
                .postId(request.postId)
                .userId(request.userId)
                .build();
    }
}
