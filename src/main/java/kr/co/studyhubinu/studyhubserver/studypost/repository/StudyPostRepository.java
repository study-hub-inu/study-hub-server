package kr.co.studyhubinu.studyhubserver.studypost.repository;

import kr.co.studyhubinu.studyhubserver.studypost.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.GetMyPostData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface StudyPostRepository extends JpaRepository<StudyPostEntity, Long>, StudyPostRepositoryCustom {

    @Query("SELECT new kr.co.studyhubinu.studyhubserver.studypost.dto.data.GetMyPostData(sp.id, sp.major, sp.title, sp.content, sp.remainingSeat, sp.close) " +
            "FROM StudyPostEntity sp " +
            "WHERE sp.postedUserId = :userId " +
            "order by sp.createdDate desc")
    Slice<GetMyPostData> findSliceByPostedUserId(Long userId, Pageable pageable);

    Long countByPostedUserId(Long userId);

    List<StudyPostEntity> findByPostedUserId(Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE StudyPostEntity sp SET sp.close = true WHERE sp.studyStartDate = :studyStartDate AND sp.close = false")
    void closeStudyPostsByStartDate(@Param("studyStartDate") LocalDate studyStartDate);
}
