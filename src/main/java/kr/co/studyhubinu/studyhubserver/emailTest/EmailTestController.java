package kr.co.studyhubinu.studyhubserver.emailTest;

import kr.co.studyhubinu.studyhubserver.email.dto.request.MailValidRequest;
import kr.co.studyhubinu.studyhubserver.email.dto.response.ValidEmailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
