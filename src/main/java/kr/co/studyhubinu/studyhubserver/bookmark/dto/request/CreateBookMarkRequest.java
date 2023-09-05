package kr.co.studyhubinu.studyhubserver.bookmark.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.studyhubinu.studyhubserver.bookmark.domain.BookMarkEntity;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CreateBookMarkRequest {


    @Schema(description = "게시글 id", example = "1")
    @NotBlank
    private Long postId;

    public CreateBookMarkRequest(Long postId) {
        this.postId = postId;
    }

    public BookMarkEntity toEntity(CreateBookMarkRequest request) {
        return BookMarkEntity.builder()
                .postId(request.postId)
                .build();
    }

}
