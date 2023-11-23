package kr.co.studyhubinu.studyhubserver.exception.study;

import kr.co.studyhubinu.studyhubserver.exception.StatusType;
import kr.co.studyhubinu.studyhubserver.exception.common.CustomException;

public class SortByNotFoundException extends CustomException {

    private final StatusType status;
    private static final String message = "해당 정렬 기준을 갖는 값이 없습니다";

    public SortByNotFoundException() {
        super(message);
        this.status = StatusType.SORT_TYPE_NOT_FOUND;
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
