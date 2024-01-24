package kr.co.studyhubinu.studyhubserver.exception.apply;

import kr.co.studyhubinu.studyhubserver.exception.StatusType;
import kr.co.studyhubinu.studyhubserver.exception.common.CustomException;

public class SameUserRequestException extends CustomException {

    private final StatusType status;
    private static final String message = "동일한 유저가 요청한 기록이 있습니다.";

    public SameUserRequestException() {
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
