package kr.co.studyhubinu.studyhubserver.study.repository;

import kr.co.studyhubinu.studyhubserver.study.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.study.dto.response.GetMyPostResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudyPostRepository extends JpaRepository<StudyPostEntity, Long>, StudyPostRepositoryCustom {

    @Query("SELECT new kr.co.studyhubinu.studyhubserver.study.dto.response.GetMyPostResponse(sp.major, sp.title, sp.content, sp.remainingSeat, sp.close) " +
            "FROM StudyPostEntity sp " +
            "WHERE sp.postedUserId = :userId")
    Slice<GetMyPostResponse> findByPostedUserId(Long userId, Pageable pageable);
}
