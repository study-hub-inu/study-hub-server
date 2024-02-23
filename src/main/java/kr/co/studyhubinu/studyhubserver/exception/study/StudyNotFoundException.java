package kr.co.studyhubinu.studyhubserver.exception.study;

import kr.co.studyhubinu.studyhubserver.exception.StatusType;
import kr.co.studyhubinu.studyhubserver.exception.common.CustomException;

public class StudyNotFoundException extends CustomException {

    private final StatusType status;
    private static final String message = "해당 식별자를 갖는 스터디가 없습니다. 식별자 값을 다시 한번 확인하세요.";
    public StudyNotFoundException() {
        super(message);
        this.status = StatusType.STUDY_NOT_FOUND;
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
