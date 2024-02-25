package kr.co.studyhubinu.studyhubserver.exception.study;

import kr.co.studyhubinu.studyhubserver.exception.StatusType;
import kr.co.studyhubinu.studyhubserver.exception.common.CustomException;

public class StudyPostClosedException extends CustomException {

    private final StatusType status;
    private static final String message = "마감된 스터디 게시글엔 지원할 수 없습니다.";

    public StudyPostClosedException() {
        super(message);
        this.status = StatusType.STUDY_POST_CLOSED;
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
