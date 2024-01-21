package kr.co.studyhubinu.studyhubserver.apply.repository;

import kr.co.studyhubinu.studyhubserver.apply.dto.data.ApplyUserData;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplyRepositoryCustom {

    List<ApplyUserData> findByStudy(Long studyId, Pageable pageable);
}
