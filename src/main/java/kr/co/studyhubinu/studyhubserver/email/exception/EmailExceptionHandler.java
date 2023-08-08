package kr.co.studyhubinu.studyhubserver.email.exception;

import kr.co.studyhubinu.studyhubserver.email.dto.response.EmailErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmailExceptionHandler {

    @ExceptionHandler(EmailException.class)
    protected ResponseEntity<EmailErrorResponse> handleMailException(EmailException emailException) {
        return EmailErrorResponse.toResponseEntity(emailException.getEmailErrorCode());
    }
}
