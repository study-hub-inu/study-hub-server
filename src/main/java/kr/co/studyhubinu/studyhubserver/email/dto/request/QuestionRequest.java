package kr.co.studyhubinu.studyhubserver.email.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.studyhubinu.studyhubserver.email.validate.NormalEmail;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class QuestionRequest {

    @Schema(description = "제목", example = "글 작성이 안되요")
    @NotBlank(message = "제목값은 필수 입니다")
    private String title;

    @Schema(description = "내용", example = "스터디 글 작성하려 했는데 어플이 꺼집니다")
    @NotBlank(message = "내용값은 필수 입니다")
    private String content;

    @Schema(description = "답변 받을 이메일", example = "kdw990202@inu.ac.kr")
    @NormalEmail
    @NotBlank(message = "이메일값은 필수 입니다")
    private String toEmail;
}
