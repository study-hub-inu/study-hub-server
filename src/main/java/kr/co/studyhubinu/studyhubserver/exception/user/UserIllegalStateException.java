package kr.co.studyhubinu.studyhubserver.exception.user;

import kr.co.studyhubinu.studyhubserver.exception.StatusType;
import kr.co.studyhubinu.studyhubserver.exception.common.CustomException;

public class UserIllegalStateException extends CustomException {
    private final StatusType status;
    private static final String message = "";

    public UserIllegalStateException() {
        super(message);
        this.status = StatusType.BAD_REQUEST;
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