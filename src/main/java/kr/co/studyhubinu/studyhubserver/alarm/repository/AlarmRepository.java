package kr.co.studyhubinu.studyhubserver.alarm.repository;

import kr.co.studyhubinu.studyhubserver.alarm.domain.AlarmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmRepository extends JpaRepository<AlarmEntity, Long>, AlarmRepositoryCustom {
}
