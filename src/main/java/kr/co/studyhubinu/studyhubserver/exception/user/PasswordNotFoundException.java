package kr.co.studyhubinu.studyhubserver.exception.user;

import kr.co.studyhubinu.studyhubserver.exception.StatusType;
import kr.co.studyhubinu.studyhubserver.exception.common.CustomException;

public class PasswordNotFoundException extends CustomException {

    private final StatusType status;

    private static final String message = "패스워드가 잘못 되었습니다.";

    public PasswordNotFoundException() {
        super(message);
        this.status = StatusType.ALREADY_USER_EXIST;
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
