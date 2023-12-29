package kr.co.studyhubinu.studyhubserver.notification.domain;

import kr.co.studyhubinu.studyhubserver.notification.enums.NotificationType;
import kr.co.studyhubinu.studyhubserver.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
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

    @Column(name = "notification_type")
    private NotificationType notificationType;

    private boolean checked;

    public void read() {
        checked = true;
    }

    public boolean isNotificationOfUser(Long userId) {
        return this.userId.equals(userId);
    }
}
