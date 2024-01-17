package kr.co.studyhubinu.studyhubserver.notification.repository;

import kr.co.studyhubinu.studyhubserver.notification.domain.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long>, NotificationRepositoryCustom {
}
