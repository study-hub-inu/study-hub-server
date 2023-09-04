package kr.co.studyhubinu.studyhubserver.exception.bookmark;

import kr.co.studyhubinu.studyhubserver.exception.StatusType;
import kr.co.studyhubinu.studyhubserver.exception.common.CustomException;

public class BookMarkNotFoundException extends CustomException {

    private final StatusType status;
    private static final String message = "해당 북마크가 존재하지 않습니다. 북마크 아이디 값을 다시 한번 확인하세요.";
    public BookMarkNotFoundException() {
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
