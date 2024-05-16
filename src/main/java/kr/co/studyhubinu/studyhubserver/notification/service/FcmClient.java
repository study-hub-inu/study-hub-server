package kr.co.studyhubinu.studyhubserver.notification.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.notification.dto.FcmMessage;
import kr.co.studyhubinu.studyhubserver.notification.dto.FcmMessage.Data;
import kr.co.studyhubinu.studyhubserver.notification.dto.FcmMessage.Message;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class FcmClient {
//
//    private static final String PREFIX_ACCESS_TOKEN = "Bearer ";
//    private static final String POSTFIX_FCM_REQUEST_URL = "/messages:send";
//    private static final String PREFIX_FCM_REQUEST_URL = "https://fcm.googleapis.com/v1/projects/";
//    private static final String FIREBASE_KEY_PATH = "firebase/firebase-service-key.json";
//    private static final String GOOGLE_AUTH_URL = "https://www.googleapis.com/auth/cloud-platform";
//
//    private final FcmTokenRepository fcmTokenRepository;
//    private final RestTemplate restTemplate;
//    private final UserRepository userRepository;
//    private final ObjectMapper objectMapper;
//
//    @Value("${firebase.project.id}")
//    private String projectId;
//
//    public void sendMessageTo(final Long receiverId, final NotificationEntity notificationEntity) {
//
//        //알림 요청 받는 사람의 FCM Token이 존재하는지 확인
//        final FcmTokenEntity fcmTokenEntity = fcmTokenRepository.findByUserId(receiverId).orElseThrow(RuntimeException::new);
//
//        //메시지 만들기
//        final String message = makeMessage(fcmTokenEntity.getToken(), notificationEntity);
//
//        final HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//        //OAuth 2.0 사용
//        httpHeaders.add(HttpHeaders.AUTHORIZATION, PREFIX_ACCESS_TOKEN + getAccessToken());
//
//        final HttpEntity<String> httpEntity = new HttpEntity<>(message, httpHeaders);
//
//        final String fcmRequestUrl = PREFIX_FCM_REQUEST_URL + projectId + POSTFIX_FCM_REQUEST_URL;
//
//        final ResponseEntity<String> exchange = restTemplate.exchange(
//                fcmRequestUrl,
//                HttpMethod.POST,
//                httpEntity,
//                String.class
//        );
//
//        if (exchange.getStatusCode().isError()) {
//            log.error("firebase 접속 에러 = {}", exchange.getBody());
//        }
//    }
//
//    private String makeMessage(final String targetToken, final NotificationEntity notificationEntity) {
//
//        final Long senderId = notificationEntity.getSenderId();
//        final UserEntity sender = userRepository.findById(senderId).orElseThrow(UserNotFoundException::new);
//
//        final Data messageData = new Data(
//                sender.getNickname(), senderId.toString(),
//                notificationEntity.getReceiverId().toString(), notificationEntity.getContent(),
//                sender.getMajor().getValue()
//        );
//
//        final Message message = new Message(messageData, targetToken);
//
//        final FcmMessage fcmMessage = new FcmMessage(false, message);
//
//        try {
//            return objectMapper.writeValueAsString(fcmMessage);
//        } catch (JsonProcessingException e) {
//            log.error("메세지 보낼 때 JSON 변환 에러", e);
//            throw new RuntimeException();
//        }
//    }
//
//    private String getAccessToken() {
//        try {
//            final GoogleCredentials googleCredentials = GoogleCredentials
//                    .fromStream(new ClassPathResource(FIREBASE_KEY_PATH).getInputStream())
//                    .createScoped(List.of(GOOGLE_AUTH_URL));
//            log.info("vvvvvvvvvvvvvvvvvvvvvvvvvvvv");
//            googleCredentials.refreshIfExpired();
//            String result =  googleCredentials.getAccessToken().getTokenValue();
//            log.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^" + result);
//            return result;
////            return googleCredentials.getAccessToken().getTokenValue();
//        } catch (IOException e) {
//            throw new RuntimeException();
//        }
//    }
//
//}
