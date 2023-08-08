package kr.co.studyhubinu.studyhubserver.email.dto.response;

import kr.co.studyhubinu.studyhubserver.email.exception.EmailErrorCode;
import kr.co.studyhubinu.studyhubserver.user.dto.response.UserErrorResponse;
import kr.co.studyhubinu.studyhubserver.user.exception.UserErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
@AllArgsConstructor
public class MailErrorResponse {

    private int status;
    private String code;
    private String msg;

    public static ResponseEntity<UserErrorResponse> toResponseEntity(EmailErrorCode e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(UserErrorResponse.builder()
                        .status(e.getStatus().value())
                        .code(e.name())
                        .msg(e.getMsg())
                        .build()
                );
    }
}
