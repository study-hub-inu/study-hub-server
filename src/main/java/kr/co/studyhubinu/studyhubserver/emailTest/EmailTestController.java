package kr.co.studyhubinu.studyhubserver.emailTest;

import kr.co.studyhubinu.studyhubserver.email.dto.request.MailValidRequest;
import kr.co.studyhubinu.studyhubserver.email.dto.response.ValidEmailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EmailTestController {

    private final EmailTestService emailTestService;

    @PostMapping("/v1/email/verify-test")
    public ResponseEntity<ValidEmailResponse> validEmail(@Valid @RequestBody MailValidRequest request) {
        boolean auth = emailTestService.validEmail(request.toService());
        return ResponseEntity.ok(new ValidEmailResponse(auth));
    }

    @PostMapping("/v1/email/verify-test-insert")
    public ResponseEntity<HttpStatus> insertEmail(@RequestParam String email, @RequestParam String authCode) {
        emailTestService.insertEmail(email, authCode);
        return ResponseEntity.ok().build();
    }
}
