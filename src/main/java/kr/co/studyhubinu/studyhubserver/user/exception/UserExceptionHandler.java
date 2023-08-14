package kr.co.studyhubinu.studyhubserver.user.exception;

import kr.co.studyhubinu.studyhubserver.exception.Exception;
import kr.co.studyhubinu.studyhubserver.user.dto.response.UserErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static kr.co.studyhubinu.studyhubserver.user.dto.response.UserErrorResponse.*;

@RestControllerAdvice
public class UserExceptionHandler implements Exception {

    @Override
    @ExceptionHandler(UserException.class)
    public ResponseEntity<UserErrorResponse> handleException(Object ex) {
        UserException userException = (UserException) ex;

        return toResponseEntity(userException.getUserErrorCode());
    }
}
