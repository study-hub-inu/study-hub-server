package kr.co.studyhubinu.studyhubserver.exception;

import lombok.Getter;

@Getter
public enum StatusType {

    BAD_REQUEST(400, "BAD_REQUEST"),
    TOKEN_EXPIRED(401, "TOKEN_EXPIRED"),
    STUDY_NOT_FOUND(404, "STUDY_NOT_FOUND"),
    POST_NOT_FOUND(404, "POST_NOT_FOUND"),
    USER_NOT_FOUND(404, "USER_NOT_FOUND"),
    COMMENT_NOT_FOUND(404, "COMMENT_NOT_FOUND"),
    INSPECTION_NOT_FOUND(404, "INSPECTION_NOT_FOUND"),
    BOOKMARK_NOT_FOUND(404, "BOOKMARK_NOT_FOUND"),
    ALREADY_USER_EXIST(409, "ALREADY_USER_EXIST"),
    TOKEN_NOT_EXIST(404, "TOKEN_NOT_EXIST"),
    ACCESS_RIGHT_FAILED(412, "ACCESS_RIGHT_FAILED"),
    DATA_CONFLICT(409, "DATA_CONFLICT"),
    NOTIFICATION_NOT_FOUND(404, "NOTIFICATION_NOT_FOUND"),
    SORT_TYPE_NOT_FOUND(404, "SORT_TYPE_NOT_FOUND"),
    MAJOR_TYPE_NOT_FOUND(404, "MAJOR_TYPE_NOT_FOUND");

    private final int statusCode;
    private final String code;

    private StatusType(int statusCode, String code) {
        this.statusCode = statusCode;
        this.code = code;
    }
}