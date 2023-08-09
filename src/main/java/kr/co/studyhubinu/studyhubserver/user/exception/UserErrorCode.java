package kr.co.studyhubinu.studyhubserver.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode {

    // == 200 == //
    SUCCESS(OK,"OK"),

    // == 4xx == //
    USER_NOT_FOUND_EXCEPTION(NOT_FOUND, "해당 email 을 가진 유저가 없습니다."),
    SAME_USER_EXCEPTION(CONFLICT, "동일한 이름을 가진 회원이 존재합니다.");

    private final HttpStatus status;
    private final String msg;
}
