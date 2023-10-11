package kr.co.studyhubinu.studyhubserver.studypost.repository;

import kr.co.studyhubinu.studyhubserver.studypost.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.GetMyPostData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudyPostRepository extends JpaRepository<StudyPostEntity, Long>, StudyPostRepositoryCustom {

    @Query("SELECT new kr.co.studyhubinu.studyhubserver.studypost.dto.data.GetMyPostData(sp.id, sp.major, sp.title, sp.content, sp.remainingSeat, sp.close) " +
            "FROM StudyPostEntity sp " +
            "WHERE sp.postedUserId = :userId")
    Slice<GetMyPostData> findByPostedUserId(Long userId, Pageable pageable);

    Long countByPostedUserId(Long userId);
}
