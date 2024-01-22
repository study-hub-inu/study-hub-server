package kr.co.studyhubinu.studyhubserver.email.controller;

import io.swagger.v3.oas.annotations.Operation;
import kr.co.studyhubinu.studyhubserver.email.dto.request.MailValidDuplicationRequest;
import kr.co.studyhubinu.studyhubserver.email.dto.request.MailValidRequest;
import kr.co.studyhubinu.studyhubserver.email.dto.request.QuestionRequest;
import kr.co.studyhubinu.studyhubserver.email.dto.response.ValidEmailResponse;
import kr.co.studyhubinu.studyhubserver.email.service.EmailService;
import kr.co.studyhubinu.studyhubserver.email.dto.request.MailSendRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EmailController {

    private final EmailService emailService;

    @Operation(summary = "이메일 인증코드 전송", description = "바디에 {email} json 형식으로 보내주시면 됩니다. ")
    @PostMapping("/v1/email")
    public ResponseEntity<HttpStatus> sendEmail(@Valid @RequestBody MailSendRequest request) throws MessagingException {
        emailService.sendEmail(request.toService());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "비밀번호 검증용 이메일 인증코드 전송", description = "바디에 {email} json 형식으로 보내주시면 됩니다. ")
    @PostMapping("/v1/email/password")
    public ResponseEntity<HttpStatus> sendEmailForChangePassword(@Valid @RequestBody MailSendRequest request) throws MessagingException {
        emailService.sendEmailForChangePassword(request.toService());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "이메일 인증코드 검증", description = "")
    @PostMapping("/v1/email/verify")
    public ResponseEntity<ValidEmailResponse> validEmail(@Valid @RequestBody MailValidRequest request) {
        boolean auth = emailService.validEmail(request.toService());
        return ResponseEntity.ok(new ValidEmailResponse(auth));
    }

    @Operation(summary = "이메일 중복 검사", description = "")
    @PostMapping("/v1/email/duplication")
    public ResponseEntity<HttpStatus> validDuplication(@Valid @RequestBody MailValidDuplicationRequest request) {
        emailService.validDuplication(request.toService());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "문의하기", description = "문의하면 저희 이메일로 오게 했습니다")
    @PostMapping("/v1/email/question")
    public ResponseEntity<HttpStatus> question(@Valid @RequestBody final QuestionRequest questionRequest) throws MessagingException {
        emailService.sendQuestionEmail(questionRequest);
        return ResponseEntity.ok().build();
    }

}
