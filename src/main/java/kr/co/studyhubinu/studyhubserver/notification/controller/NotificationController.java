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
    @PutMapping("/v1/notification/{notification_id}")
    public ResponseEntity<HttpStatus> readNotification(@PathVariable("notification_id") Long notificationId, UserId userId) {
        notificationService.readNotification(notificationId, userId.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "알람 삭제", description = "사용자가 알람을 삭제 합니다.")
    @DeleteMapping("/v1/notification/{notification_id}")
    public ResponseEntity<HttpStatus> deleteNotification(@PathVariable("notification_id") Long alarmId, UserId userId) {
        notificationService.deleteNotification(alarmId, userId.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Operation(summary = "알림 조회", description = "사용자의 모든 알림 메시지를 조회합니다.")
    @GetMapping("v1/notification")
    public ResponseEntity<Slice<NotificationResponse>> getNotifications(@RequestParam int page, @RequestParam int size, UserId userId) {
        Slice<NotificationResponse> alarms = notificationService.getNotification(page, size, userId.getId());
        return new ResponseEntity(alarms, HttpStatus.OK);
    }

    @Operation(summary = "FCM 토큰 저장", description = "발급받으신 fcm 토큰을 보내주시면 됩니다")
    @PostMapping("v1/notification/token")
    public void createFcmToken(@RequestBody final CreateFcmTokenRequest createFcmTokenRequest, UserId userId) {
        fcmTokenService.createFcmToken(createFcmTokenRequest, userId.getId());
    }

}
