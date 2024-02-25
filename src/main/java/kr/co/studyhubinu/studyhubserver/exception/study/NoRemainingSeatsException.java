package kr.co.studyhubinu.studyhubserver.exception.study;

import kr.co.studyhubinu.studyhubserver.exception.StatusType;
import kr.co.studyhubinu.studyhubserver.exception.common.CustomException;

public class NoRemainingSeatsException extends CustomException {

    private final StatusType status;
    private static final String message = "잔여석은 0개가 될 수 없습니다";

    public NoRemainingSeatsException() {
        super(message);
        this.status = StatusType.NO_REMAINING_SEAT;
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
