package kr.co.studyhubinu.studyhubserver.exception.user;

import kr.co.studyhubinu.studyhubserver.exception.StatusType;
import kr.co.studyhubinu.studyhubserver.exception.common.CustomException;

public class UserNotAccessRightException extends CustomException {

    private final StatusType status;

    private static final String message = "접근권한이 없는 유저입니다";

    public UserNotAccessRightException() {
        super(message);
        this.status = StatusType.ACCESS_RIGHT_FAILED;
    }

    @Override
    public StatusType getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}