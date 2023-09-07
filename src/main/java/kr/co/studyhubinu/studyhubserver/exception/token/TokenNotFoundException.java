package kr.co.studyhubinu.studyhubserver.exception.token;


import kr.co.studyhubinu.studyhubserver.exception.StatusType;
import kr.co.studyhubinu.studyhubserver.exception.common.CustomException;

public class TokenNotFoundException extends CustomException {
    private final StatusType status;
    private static final String message = "해당 토큰이 만료되었습니다. 재발급 요청을 보내주세요";

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