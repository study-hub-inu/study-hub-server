package kr.co.studyhubinu.studyhubserver.apply.repository;

import kr.co.studyhubinu.studyhubserver.apply.domain.RejectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RejectRepository extends JpaRepository<RejectEntity, Long> {
}
