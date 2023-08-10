package kr.co.studyhubinu.studyhubserver.exception;

import org.springframework.http.ResponseEntity;

public interface Exception {
    ResponseEntity<?> handleException(Object ex);
}
