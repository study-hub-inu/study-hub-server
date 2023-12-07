package kr.co.studyhubinu.studyhubserver.exception.image;

import kr.co.studyhubinu.studyhubserver.exception.StatusType;
import kr.co.studyhubinu.studyhubserver.exception.common.CustomException;

public class ImageSizeException extends CustomException {

    private final StatusType status;

    private static final String message = "이미지의 크기는 20MB 미만이어야 합니다.";

    public ImageSizeException() {
        super(message);
        this.status = StatusType.BAD_REQUEST;
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
