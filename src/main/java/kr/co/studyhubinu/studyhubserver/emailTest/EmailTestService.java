package kr.co.studyhubinu.studyhubserver.emailTest;

import kr.co.studyhubinu.studyhubserver.common.timer.Timer;
import kr.co.studyhubinu.studyhubserver.email.dto.data.ValidMailInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailTestService {

    private final EmailTestRepository emailTestRepository;

    @Timer
    public boolean validEmail(ValidMailInfo info) {
        EmailTestEntity email = emailTestRepository.findByEmail(info.getEmail());
        log.info("**************************저장된 인증 코드" + email.getCode());
        log.info("**************************입력된 인증 코드" + info.getAuthCode());
        return email != null && email.getCode().equals(info.getAuthCode());
    }

    public void insertEmail(String email, String authCode) {
        EmailTestEntity emailTestEntity = EmailTestEntity.builder()
                .email(email)
                .code(authCode)
                .build();
        emailTestRepository.save(emailTestEntity);
    }
}
