package kr.co.studyhubinu.studyhubserver.exception;

import lombok.Getter;

@Getter
public enum StatusType {

    BAD_REQUEST(400, "BAD_REQUEST"),
    TOKEN_EXPIRED(401, "TOKEN_EXPIRED"),
    VOTE_NOT_FOUND(404, "STUDY_NOT_FOUND"),
    USER_NOT_FOUND(404, "USER_NOT_FOUND"),
    COMMENT_NOT_FOUND(404, "COMMENT_NOT_FOUND"),
    ALREADY_USER_EXIST(403, "ALREADY_USER_EXIST"),
    TOKEN_NOT_EXIST(404, "TOKEN_NOT_EXIST"),
    ACCESS_RIGHT_FAILED(412, "ACCESS_RIGHT_FAILED");


    private final int statusCode;
    private final String code;

    private StatusType(int statusCode, String code) {
        this.statusCode = statusCode;
        this.code = code;
    }
}