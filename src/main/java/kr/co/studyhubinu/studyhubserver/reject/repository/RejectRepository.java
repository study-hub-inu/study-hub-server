package kr.co.studyhubinu.studyhubserver.reject.repository;

import kr.co.studyhubinu.studyhubserver.reject.domain.RejectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RejectRepository extends JpaRepository<RejectEntity, Long>, RejectRepositoryCustom {
}
