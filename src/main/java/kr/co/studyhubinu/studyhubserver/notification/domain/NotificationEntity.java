package kr.co.studyhubinu.studyhubserver.notification.domain;

import kr.co.studyhubinu.studyhubserver.notification.enums.NotificationType;
import kr.co.studyhubinu.studyhubserver.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "notification")
public class NotificationEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "reciver_id")
    private Long receiverId;

    @Column(name = "sender_id")
    private Long senderId;

    @Column(name = "content")
    private String content;

    @Column(name = "notification_type")
    private NotificationType notificationType;

    private boolean checked;

    public NotificationEntity(Long userId, Long postId, Long receiverId, Long senderId, String content, NotificationType notificationType, boolean checked) {
        this.userId = userId;
        this.postId = postId;
        this.receiverId = receiverId;
        this.senderId = senderId;
        this.content = content;
        this.notificationType = notificationType;
        this.checked = checked;
    }

    @Builder


    public void read() {
        checked = true;
    }

    public boolean isNotificationOfUser(Long userId) {
        return this.userId.equals(userId);
    }
}
