package kr.co.studyhubinu.studyhubserver.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class ExceptionMessage {
    private StatusType status;
    private String message;

    public static ExceptionMessage of(StatusType status, String message) {
        return ExceptionMessage.builder().status(status)
                .message(message)
                .build();
    }

    public static ExceptionMessage of(String msg) {
        return ExceptionMessage.of(StatusType.BAD_REQUEST, msg);
    }
}