package kr.co.studyhubinu.studyhubserver.exception.apply;

import kr.co.studyhubinu.studyhubserver.exception.StatusType;
import kr.co.studyhubinu.studyhubserver.exception.common.CustomException;

public class ApplyNotFoundException extends CustomException {

    private final StatusType status;
    private static final String message = "조회되는 요청이 없습니다.";

    public ApplyNotFoundException() {
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
