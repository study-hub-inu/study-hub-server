package kr.co.studyhubinu.studyhubserver.exception.user;


import kr.co.studyhubinu.studyhubserver.exception.StatusType;
import kr.co.studyhubinu.studyhubserver.exception.common.CustomException;

public class AlreadyExistUserException extends CustomException {

    private final StatusType status;

    private static final String message = "이미 해당 이메일로 회원가입 한 유저입니다.";

    public AlreadyExistUserException() {
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