package kr.co.studyhubinu.studyhubserver.exception.apply;

import kr.co.studyhubinu.studyhubserver.exception.StatusType;
import kr.co.studyhubinu.studyhubserver.exception.common.CustomException;

public class LockAcquisitionException extends CustomException {

    private final StatusType status;
    private static final String message = "스터디 지원에 대한 락 획득에 실패했습니다.";

    public LockAcquisitionException() {
        super(message);
        this.status = StatusType.STUDY_APPLY_LOCK_ACQUISITION;
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
