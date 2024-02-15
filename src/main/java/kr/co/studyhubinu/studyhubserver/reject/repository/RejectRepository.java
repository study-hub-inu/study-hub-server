package kr.co.studyhubinu.studyhubserver.reject.repository;

import kr.co.studyhubinu.studyhubserver.reject.domain.RejectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RejectRepository extends JpaRepository<RejectEntity, Long>, RejectRepositoryCustom {
}
