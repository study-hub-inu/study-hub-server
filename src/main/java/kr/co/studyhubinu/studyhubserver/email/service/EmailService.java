package kr.co.studyhubinu.studyhubserver.email.service;

import kr.co.studyhubinu.studyhubserver.email.dto.data.MailInfo;
import kr.co.studyhubinu.studyhubserver.email.dto.data.ValidDuplicationInfo;
import kr.co.studyhubinu.studyhubserver.email.dto.data.ValidMailInfo;
import kr.co.studyhubinu.studyhubserver.exception.user.AlreadyExistUserException;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class EmailService {

    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;
    private final EmailCacheService emailCacheService;
    private final StringRedisTemplate redisTemplate;
    private final UserRepository userRepository;

    @Value("${spring.mail.username}")
    private String emailAddress;

    //이메일 보낼 양식
    public MimeMessage createEmailForm(String email) throws MessagingException {

        String authCode = emailCacheService.getAndCacheAuthCode(email); // 캐시된 인증 코드 가져오기
        String setFrom = emailAddress; //email-config 에 설정한 자신의 이메일 주소(보내는 사람)
        String toEmail = email; //받는 사람
        String title = "스터디허브 이메일 인증 번호"; //제목
        log.info("**************************발급된 인증 코드" + authCode);
        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, email); //보낼 이메일 설정
        message.setSubject(title); //제목 설정
        message.setFrom(setFrom); //보내는 이메일
        message.setText(setContext(authCode), "utf-8", "html");

        return message;
    }

    public void sendEmail(MailInfo info) throws MessagingException {

        String email = info.getEmail();
        MimeMessage emailForm = createEmailForm(email);
        emailSender.send(emailForm);

    }

    public String setContext(String code) {

        Context context = new Context();
        context.setVariable("code", code);
        return templateEngine.process("mail", context); //mail.html

    }


    public boolean validEmail(ValidMailInfo info) {

        String cachedAuthCode = redisTemplate.opsForValue().get(info.getEmail()); // 캐시된 인증 코드 가져오기
        log.info("**************************캐싱된 인증 코드" + cachedAuthCode);
        log.info("**************************입력된 인증 코드" + info.getAuthCode());
        return cachedAuthCode != null && cachedAuthCode.equals(info.getAuthCode());
    }

    public void validDuplication(ValidDuplicationInfo info) {
        if (userRepository.existsByEmail(info.getEmail())) {
            throw new AlreadyExistUserException();
        }
    }
}