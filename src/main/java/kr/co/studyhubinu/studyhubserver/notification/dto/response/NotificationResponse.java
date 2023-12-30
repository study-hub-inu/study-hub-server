package kr.co.studyhubinu.studyhubserver.notification.dto.response;

import kr.co.studyhubinu.studyhubserver.notification.enums.NotificationType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NotificationResponse {

    private Long notificationId;
    private Long postId;
    private Long userId;
    private NotificationType notificationType;
    private String postTitle;
    private boolean isChecked;
    private LocalDateTime createdDate;

    public NotificationResponse(Long notificationId, Long postId, Long userId, NotificationType notificationType, String postTitle, boolean isChecked, LocalDateTime createdDate) {
        this.notificationId = notificationId;
        this.postId = postId;
        this.userId = userId;
        this.notificationType = notificationType;
        this.postTitle = postTitle;
        this.isChecked = isChecked;
        this.createdDate = createdDate;
    }
}
