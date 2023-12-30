package kr.co.studyhubinu.studyhubserver.notification.service;

import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.notification.domain.FcmTokenEntity;
import kr.co.studyhubinu.studyhubserver.notification.dto.request.CreateFcmTokenRequest;
import kr.co.studyhubinu.studyhubserver.notification.repository.FcmTokenRepository;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FcmTokenService {

    private final FcmTokenRepository fcmTokenRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createFcmToken(CreateFcmTokenRequest createFcmTokenRequest, Long userId) {
        validateUserExist(userId);
        fcmTokenRepository.findByUserId(userId).ifPresentOrElse(
                fcmTokenEntity -> fcmTokenEntity.updateToken(createFcmTokenRequest.getFcmToken()),
                () -> fcmTokenRepository.save(new FcmTokenEntity(userId, createFcmTokenRequest.getFcmToken()))
        );

    }

    @Transactional
    public void deleteFcmToken(Long userId) {
        validateUserExist(userId);
        fcmTokenRepository.deleteByUserId(userId);
    }

    private void validateUserExist(Long userId) {
        userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }
}
