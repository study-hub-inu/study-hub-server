package kr.co.studyhubinu.studyhubserver.notification.controller;

import io.swagger.v3.oas.annotations.Operation;
import kr.co.studyhubinu.studyhubserver.notification.dto.request.CreateFcmTokenRequest;
import kr.co.studyhubinu.studyhubserver.notification.dto.response.NotificationResponse;
import kr.co.studyhubinu.studyhubserver.notification.service.FcmTokenService;
import kr.co.studyhubinu.studyhubserver.notification.service.NotificationService;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NotificationController {

    private final NotificationService notificationService;
    private final FcmTokenService fcmTokenService;
    @Operation(summary = "알림 읽음 처리", description = "사용자가 클릭한 알림을 읽음 처리 합니다.")
    @PutMapping("/v1/notification/{notification-id}")
    public ResponseEntity<HttpStatus> readNotification(@PathVariable("notification-id") Long notificationId, UserId userId) {
        notificationService.readNotification(notificationId, userId.getId());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "알람 삭제", description = "사용자가 알람을 삭제 합니다.")
    @DeleteMapping("/v1/notification/{notification-id}")
    public ResponseEntity<HttpStatus> deleteNotification(@PathVariable("notification-id") Long alarmId, UserId userId) {
        notificationService.deleteNotification(alarmId, userId.getId());
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "알림 조회", description = "사용자의 모든 알림 메시지를 조회합니다.")
    @GetMapping("/v1/notification")
    public ResponseEntity<Slice<NotificationResponse>> getNotifications(@RequestParam int page, @RequestParam int size, UserId userId) {
        Slice<NotificationResponse> alarms = notificationService.getNotification(page, size, userId.getId());
        return ResponseEntity.ok().body(alarms);
    }

    @Operation(summary = "FCM 토큰 저장", description = "발급받으신 fcm 토큰을 보내주시면 됩니다")
    @PostMapping("/v1/notification/token")
    public ResponseEntity<HttpStatus> createFcmToken(@RequestBody final CreateFcmTokenRequest createFcmTokenRequest, UserId userId) {
        fcmTokenService.createFcmToken(createFcmTokenRequest, userId.getId());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "FCM 토큰 삭제", description = "회원이 로그아웃 하면")
    @DeleteMapping("/v1/notification/token")
    public ResponseEntity<HttpStatus> deleteFcmToken(UserId userId) {
        fcmTokenService.deleteFcmToken(userId.getId());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "알림 기능 테스트", description = "알림 기능을 테스트하겠습니다")
    @PostMapping("/vi/notification/test")
    public ResponseEntity<HttpStatus> sendNotification(UserId userId) {
        notificationService.sendTest(userId.getId());
        return ResponseEntity.ok().build();
    }

}
