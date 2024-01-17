package kr.co.studyhubinu.studyhubserver.notification.service;

import kr.co.studyhubinu.studyhubserver.notification.domain.NotificationEntity;
import kr.co.studyhubinu.studyhubserver.notification.dto.response.NotificationResponse;
import kr.co.studyhubinu.studyhubserver.notification.enums.NotificationType;
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
    private final FcmClient fcmClient;

    public void readNotification(Long notificationId, Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        NotificationEntity notification = notificationRepository.findById(notificationId).orElseThrow(NotificationNotFoundException::new);
        validIsNotificationOfReceiver(user.getId(), notification);
        notification.read();
    }

    public void deleteNotification(Long notificationId, Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        NotificationEntity notification = notificationRepository.findById(notificationId).orElseThrow(NotificationNotFoundException::new);
        validIsNotificationOfReceiver(user.getId(), notification);
        notificationRepository.delete(notification);
    }

    private void validIsNotificationOfReceiver(Long receiverId, NotificationEntity notification) {
        if (!notification.isNotificationOfUser(receiverId)) throw new UserNotAccessRightException();
    }

    public Slice<NotificationResponse> getNotification(int page, int size, Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        return notificationRepository.findNotificationByReceiverId(user.getId(), pageable);
    }

    public void sendTest(Long userId) {

        NotificationEntity notificationEntity = NotificationEntity.builder()
                .postId(10L)
                .receiverId(userId)
                .senderId(5L)
                .content("푸시알림에 성공하신걸 축하드립니다 고생 많으셨습니다!")
                .notificationType(NotificationType.RECRUIT)
                .checked(false)
                .build();

        fcmClient.sendMessageTo(userId, notificationEntity);

    }
}
