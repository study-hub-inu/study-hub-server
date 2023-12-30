package kr.co.studyhubinu.studyhubserver.notification.repository;

import kr.co.studyhubinu.studyhubserver.notification.domain.FcmTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FcmTokenRepository extends JpaRepository<FcmTokenEntity, Long> {
    Optional<FcmTokenEntity> findByUserId(Long userId);

    void deleteByUserId(Long userId);
}
