package kr.co.studyhubinu.studyhubserver.alarm.repository;

import kr.co.studyhubinu.studyhubserver.alarm.dto.response.AlarmResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface AlarmRepositoryCustom {
    Slice<AlarmResponse> findAlarmByuserId(Long userId, Pageable pageable);
}
