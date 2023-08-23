package kr.co.studyhubinu.studyhubserver.exception.user;

import kr.co.studyhubinu.studyhubserver.exception.StatusType;
import lombok.Getter;
import org.springframework.dao.EmptyResultDataAccessException;

@Getter
public class UserEmptyResultDataAccessException extends EmptyResultDataAccessException {

    private final StatusType status;

    private static final String message = "해당 아이디를 가진 유저가 없습니다. 아이디 값을 다시 한번 확인하세요.";

    public UserEmptyResultDataAccessException(int expectedSize) {
        super(message, expectedSize);
        this.status = StatusType.USER_NOT_FOUND;
    }
}