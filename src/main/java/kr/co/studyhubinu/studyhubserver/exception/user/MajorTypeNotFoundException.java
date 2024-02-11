package kr.co.studyhubinu.studyhubserver.exception.user;

import kr.co.studyhubinu.studyhubserver.exception.StatusType;
import kr.co.studyhubinu.studyhubserver.exception.common.CustomException;

public class MajorTypeNotFoundException extends CustomException {
    private final StatusType status;
    private static final String message = "전공 타입을 찾을 수 없습니다";

    public MajorTypeNotFoundException() {
        super(message);
        this.status = StatusType.MAJOR_TYPE_NOT_FOUND;
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
