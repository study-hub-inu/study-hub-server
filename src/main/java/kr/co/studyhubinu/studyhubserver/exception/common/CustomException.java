package kr.co.studyhubinu.studyhubserver.exception.common;


import kr.co.studyhubinu.studyhubserver.exception.StatusType;

public abstract class CustomException extends RuntimeException{

    public abstract StatusType getStatus();

    public abstract String getMessage();

    public CustomException(String message) {
        super(message);
    }
}
