package kr.co.studyhubinu.studyhubserver.notification.service;

import kr.co.studyhubinu.studyhubserver.notification.domain.NotificationEntity;
import kr.co.studyhubinu.studyhubserver.notification.dto.response.NotificationResponse;
import kr.co.studyhubinu.studyhubserver.notification.repository.NotificationRepository;
import kr.co.studyhubinu.studyhubserver.exception.notification.NotificationNotFoundException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotAccessRightException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class NotificationService {

    private final UserRepository userRepository;

    private final NotificationRepository notificationRepository;

    public void readNotification(Long notificationId, Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        NotificationEntity notification = notificationRepository.findById(notificationId).orElseThrow(NotificationNotFoundException::new);
        validIsNotificationOfUser(user.getId(), notification);
        notification.read();
    }

    public void deleteNotification(Long notificationId, Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        NotificationEntity notification = notificationRepository.findById(notificationId).orElseThrow(NotificationNotFoundException::new);
        validIsNotificationOfUser(user.getId(), notification);
        notificationRepository.delete(notification);
    }

    private void validIsNotificationOfUser(Long notificationId, NotificationEntity notification) {
        if (!notification.isNotificationOfUser(notificationId)) throw new UserNotAccessRightException();
    }

    public Slice<NotificationResponse> getNotification(int page, int size, Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        return notificationRepository.findNotificationByuserId(user.getId(), pageable);
    }
}
