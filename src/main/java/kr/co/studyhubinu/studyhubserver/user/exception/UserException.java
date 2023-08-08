package kr.co.studyhubinu.studyhubserver.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserException extends RuntimeException {

    private final UserErrorCode userErrorCode;
}
