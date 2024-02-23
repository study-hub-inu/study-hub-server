package kr.co.studyhubinu.studyhubserver.studypost.repository;

import kr.co.studyhubinu.studyhubserver.studypost.domain.StudyPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StudyPostRepository extends JpaRepository<StudyPostEntity, Long>, StudyPostRepositoryCustom {

    Long countByPostedUserId(Long userId);

    List<StudyPostEntity> findByPostedUserId(Long id);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE StudyPostEntity sp SET sp.close = true WHERE sp.studyStartDate = :studyStartDate AND sp.close = false")
    void closeStudyPostsByStartDate(@Param("studyStartDate") LocalDate studyStartDate);

    Optional<StudyPostEntity> findByStudyId(Long studyId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM StudyPostEntity sp WHERE sp.postedUserId = :userId")
    void deleteAllStudyPostByUserId(@Param("userId") Long userId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from StudyPostEntity s where s.studyId = :studyId")
    Optional<StudyPostEntity> findByIdWithPessimisticLock(@Param("studyId") Long studyId);
}
