package kr.co.studyhubinu.studyhubserver.exception.notification;

import kr.co.studyhubinu.studyhubserver.exception.StatusType;
import kr.co.studyhubinu.studyhubserver.exception.common.CustomException;

public class NotificationNotFoundException extends CustomException {
    private final StatusType status;
    private static final String message = "해당 아이디를 가진 알람이 없습니다. 아이디 값을 다시 한번 확인하세요.";

    public NotificationNotFoundException() {
        super(message);
        this.status = StatusType.NOTIFICATION_NOT_FOUND;
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
