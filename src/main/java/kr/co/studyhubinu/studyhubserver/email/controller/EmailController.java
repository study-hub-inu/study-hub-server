package kr.co.studyhubinu.studyhubserver.email.controller;

import io.swagger.v3.oas.annotations.Operation;
import kr.co.studyhubinu.studyhubserver.email.dto.request.MailValidRequest;
import kr.co.studyhubinu.studyhubserver.email.dto.response.ValidEmailResponse;
import kr.co.studyhubinu.studyhubserver.email.service.EmailService;
import kr.co.studyhubinu.studyhubserver.email.dto.request.MailSendRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;

    @Operation(summary = "이메일 인증코드 전송", description = "바디에 {email} json 형식으로 보내주시면 됩니다. ")
    @PostMapping("/send")
    public ResponseEntity sendEmail(@Valid @RequestBody MailSendRequest request) throws MessagingException {

        emailService.sendEmail(request.toService());

        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "이메일 인증코드 검증", description = "")
    @PostMapping("/valid")
    public ResponseEntity<ValidEmailResponse> validEmail(@Valid @RequestBody MailValidRequest request) {

        boolean auth = emailService.validEmail(request.toService());

        return new ResponseEntity(new ValidEmailResponse(auth), HttpStatus.OK);

    }

    @Operation(summary = "캐싱 테스트", description = "ㅇㅇ")
    @GetMapping("/test/{number}")
    public ResponseEntity<String> test(@PathVariable String number) {

        String result = emailService.test(number);

        return new ResponseEntity(result, HttpStatus.OK);
    }


}
