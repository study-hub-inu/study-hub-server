package kr.co.studyhubinu.studyhubserver.exception.study;

import kr.co.studyhubinu.studyhubserver.exception.StatusType;
import kr.co.studyhubinu.studyhubserver.exception.common.CustomException;

public class PostNotFoundExceptionByStudyId extends CustomException {

    private final StatusType status;
    private static final String message = "해당 스터디 아이디를 가진 게시글이 없습니다.";

    public PostNotFoundExceptionByStudyId() {
        super(message);
        this.status = StatusType.POST_NOT_FOUND;
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
