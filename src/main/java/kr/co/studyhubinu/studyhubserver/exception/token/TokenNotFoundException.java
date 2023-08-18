package kr.co.studyhubinu.studyhubserver.exception.token;


import kr.co.studyhubinu.studyhubserver.exception.StatusType;
import kr.co.studyhubinu.studyhubserver.exception.common.CustomException;

public class TokenNotFoundException extends CustomException {
    private final StatusType status;
    private static final String message = "해당 아이디를 가진 유저가 없습니다. 아이디 값을 다시 한번 확인하세요.";

    public TokenNotFoundException() {
        super(message);
        this.status = StatusType.TOKEN_NOT_EXIST;
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