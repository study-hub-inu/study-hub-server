package kr.co.studyhubinu.studyhubserver.exception.comment;

import kr.co.studyhubinu.studyhubserver.exception.StatusType;
import kr.co.studyhubinu.studyhubserver.exception.common.CustomException;

public class CommentNotFoundException extends CustomException {

    private final StatusType status;
    private static final String message = "해당 아이디를 가진 댓글이 없습니다. 아이디 값을 다시 한번 확인하세요.";
    public CommentNotFoundException() {
        super(message);
        this.status = StatusType.COMMENT_NOT_FOUND;
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