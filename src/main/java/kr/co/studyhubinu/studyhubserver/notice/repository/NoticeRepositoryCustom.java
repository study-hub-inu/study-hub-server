package kr.co.studyhubinu.studyhubserver.notice.repository;

import kr.co.studyhubinu.studyhubserver.notice.dto.response.FindNoticeResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeRepositoryCustom {
    List<FindNoticeResponse> findNoticeAll(Pageable pageable);
}
