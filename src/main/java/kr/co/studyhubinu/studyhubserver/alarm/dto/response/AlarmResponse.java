package kr.co.studyhubinu.studyhubserver.alarm.dto.response;

import kr.co.studyhubinu.studyhubserver.alarm.enums.AlarmType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AlarmResponse {

    private Long alarmId;
    private Long postId;
    private Long userId;
    private AlarmType alarmCategory;
    private String postTitle;
    private boolean isChecked;
    private LocalDateTime createdDate;

    public AlarmResponse(Long alarmId, Long postId, Long userId, AlarmType alarmCategory, String postTitle, boolean isChecked, LocalDateTime createdDate) {
        this.alarmId = alarmId;
        this.postId = postId;
        this.userId = userId;
        this.alarmCategory = alarmCategory;
        this.postTitle = postTitle;
        this.isChecked = isChecked;
        this.createdDate = createdDate;
    }
}
