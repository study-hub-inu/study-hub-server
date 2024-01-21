package kr.co.studyhubinu.studyhubserver.exception.apply;

import kr.co.studyhubinu.studyhubserver.exception.StatusType;
import kr.co.studyhubinu.studyhubserver.exception.common.CustomException;

public class InspectionNotFoundException extends CustomException {

    private final StatusType status;
    private static final String message = "해당 이름을 가진 상태가 없습니다. 상태정보는 [Accept, StandBy, Reject] 중 하나입니다.";

    public InspectionNotFoundException() {
        super(message);
        this.status = StatusType.BOOKMARK_NOT_FOUND;
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
