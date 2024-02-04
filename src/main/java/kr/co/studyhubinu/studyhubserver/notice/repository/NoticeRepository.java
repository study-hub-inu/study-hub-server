package kr.co.studyhubinu.studyhubserver.notice.repository;

import kr.co.studyhubinu.studyhubserver.notice.domain.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Long>, NoticeRepositoryCustom{
}
