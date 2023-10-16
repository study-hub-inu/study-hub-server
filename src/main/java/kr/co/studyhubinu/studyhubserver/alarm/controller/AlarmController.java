package kr.co.studyhubinu.studyhubserver.alarm.controller;

import io.swagger.v3.oas.annotations.Operation;
import kr.co.studyhubinu.studyhubserver.alarm.service.AlarmService;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/alarm")
public class AlarmController {

    private final AlarmService alarmService;

    @Operation(summary = "알림 읽음 처리", description = "사용자가 클릭한 알림을 읽음 처리 합니다.")
    @PostMapping("/{alarm_id}/read")
    public ResponseEntity<HttpStatus> readAlarm(@PathVariable("alarm_id") Long alarm_id, UserId userId) {
        alarmService.readAlarm(alarm_id, userId.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
