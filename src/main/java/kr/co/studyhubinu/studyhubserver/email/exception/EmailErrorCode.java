package kr.co.studyhubinu.studyhubserver.email.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@Getter
@RequiredArgsConstructor
public enum EmailErrorCode {
    // == 200 == //
    SUCCESS(OK,"OK"),

    // == 4xx == //
    EMAIL_NOT_FOUND_EXCEPTION(NOT_FOUND, "해당 email 를 가진 유저가 없습니다.");

    private final HttpStatus status;
    private final String msg;
}
