package kr.co.studyhubinu.studyhubserver.user.exception;

import kr.co.studyhubinu.studyhubserver.user.dto.response.UserErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static kr.co.studyhubinu.studyhubserver.user.dto.response.UserErrorResponse.*;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserException.class)
    protected ResponseEntity<UserErrorResponse> handleUserException(UserException userException) {
        return toResponseEntity(userException.getUserErrorCode());
    }
}
