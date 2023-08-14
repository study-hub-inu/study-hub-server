package kr.co.studyhubinu.studyhubserver.email.exception;

import kr.co.studyhubinu.studyhubserver.email.dto.response.EmailErrorResponse;
import kr.co.studyhubinu.studyhubserver.exception.Exception;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static kr.co.studyhubinu.studyhubserver.email.dto.response.EmailErrorResponse.*;


@RestControllerAdvice
public class EmailExceptionHandler implements Exception {

    @Override
    @ExceptionHandler(EmailException.class)
    public ResponseEntity<EmailErrorResponse> handleException(Object ex) {
        EmailException emailException = (EmailException) ex;

        return toResponseEntity(emailException.getEmailErrorCode());
    }
}
