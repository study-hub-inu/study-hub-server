package kr.co.studyhubinu.studyhubserver.email.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EmailException extends RuntimeException {

    private final EmailErrorCode emailErrorCode;
}
