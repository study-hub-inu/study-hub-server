package kr.co.studyhubinu.studyhubserver.email.dto.response;

import kr.co.studyhubinu.studyhubserver.email.exception.EmailErrorCode;
import kr.co.studyhubinu.studyhubserver.user.dto.response.UserErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
@AllArgsConstructor
public class EmailErrorResponse {

    private int status;
    private String code;
    private String msg;

    public static ResponseEntity<EmailErrorResponse> toResponseEntity(EmailErrorCode e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(EmailErrorResponse.builder()
                        .status(e.getStatus().value())
                        .code(e.name())
                        .msg(e.getMsg())
                        .build()
                );
    }
}
