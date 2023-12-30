package kr.co.studyhubinu.studyhubserver.notification.domain;

import kr.co.studyhubinu.studyhubserver.notification.enums.NotificationType;
import kr.co.studyhubinu.studyhubserver.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "receiver_id")
    private Long receiverId;

    @Column(name = "sender_id")
    private Long senderId;

    @Column(name = "content")
    private String content;

    @Column(name = "notification_type")
    private NotificationType notificationType;

    @ColumnDefault("false")
    private boolean checked;

    @Builder
    public NotificationEntity(Long postId, Long receiverId, Long senderId, String content, NotificationType notificationType, boolean checked) {
        this.postId = postId;
        this.receiverId = receiverId;
        this.senderId = senderId;
        this.content = content;
        this.notificationType = notificationType;
        this.checked = checked;
    }

    public void read() {
        checked = true;
    }

    public boolean isNotificationOfUser(Long receiverId) {
        return this.receiverId.equals(receiverId);
    }
}
