package kr.co.studyhubinu.studyhubserver.user.repository;

import kr.co.studyhubinu.studyhubserver.study.dto.response.FindPostResponseByAll;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface UserRepositoryCustom {

    Slice<FindPostResponseByAll> findMyPost(Long userId, Pageable pageable);

}
