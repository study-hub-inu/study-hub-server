package kr.co.studyhubinu.studyhubserver.exception.study;

import kr.co.studyhubinu.studyhubserver.exception.StatusType;
import kr.co.studyhubinu.studyhubserver.exception.common.CustomException;

public class PostStartDateConflictException extends CustomException {

    private final StatusType status;

    private static final String message = "시작날짜는 금일보다 뒤에 있어야 합니다.";

    public PostStartDateConflictException() {
        super(message);
        this.status = StatusType.DATA_CONFLICT;
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
