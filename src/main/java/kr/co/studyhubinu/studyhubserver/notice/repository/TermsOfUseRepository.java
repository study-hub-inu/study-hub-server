package kr.co.studyhubinu.studyhubserver.notice.repository;

import kr.co.studyhubinu.studyhubserver.notice.domain.TermsOfUseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermsOfUseRepository extends JpaRepository<TermsOfUseEntity, Long>, TermsOfUseRepositoryCustom {
}
