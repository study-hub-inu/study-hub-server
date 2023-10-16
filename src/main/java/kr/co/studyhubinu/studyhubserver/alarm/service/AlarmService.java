package kr.co.studyhubinu.studyhubserver.alarm.service;

import kr.co.studyhubinu.studyhubserver.alarm.domain.AlarmEntity;
import kr.co.studyhubinu.studyhubserver.alarm.repository.AlarmRepository;
import kr.co.studyhubinu.studyhubserver.exception.alarm.AlarmNotFoundException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AlarmService {

    private final UserRepository userRepository;

    private final AlarmRepository alarmRepository;

    public void readAlarm(Long alarmId, Long userId) {
        userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        AlarmEntity alarm = alarmRepository.findById(alarmId).orElseThrow(AlarmNotFoundException::new);
        alarm.read();
    }


}
