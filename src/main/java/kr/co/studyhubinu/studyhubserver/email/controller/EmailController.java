package kr.co.studyhubinu.studyhubserver.email.controller;

import io.swagger.v3.oas.annotations.Operation;
import kr.co.studyhubinu.studyhubserver.email.dto.response.MailSendResponse;
import kr.co.studyhubinu.studyhubserver.email.service.EmailService;
import kr.co.studyhubinu.studyhubserver.email.dto.request.MailSendRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;

    @Operation(summary = "이메일 인증", description = "바디에 {email} json 형식으로 보내주시면 됩니다. ")
    @PostMapping("/mail-send")
    public ResponseEntity<MailSendResponse> validEmail(@Valid @RequestBody MailSendRequest request) throws MessagingException {

        String code = emailService.validEmail(request);

        return new ResponseEntity(new MailSendResponse(code), HttpStatus.OK);
    }

}
