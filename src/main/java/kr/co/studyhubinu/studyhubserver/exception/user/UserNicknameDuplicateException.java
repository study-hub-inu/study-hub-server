package kr.co.studyhubinu.studyhubserver.exception.user;

import kr.co.studyhubinu.studyhubserver.exception.StatusType;
import kr.co.studyhubinu.studyhubserver.exception.common.CustomException;

public class UserNicknameDuplicateException extends CustomException {
    private final StatusType status;
    private static final String message = "이미 사용중인 닉네임 입니다";

    public UserNicknameDuplicateException() {
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
