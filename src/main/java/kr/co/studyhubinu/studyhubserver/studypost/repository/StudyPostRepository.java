package kr.co.studyhubinu.studyhubserver.studypost.repository;

import kr.co.studyhubinu.studyhubserver.studypost.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.GetMyPostData;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.RelatedPostData;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudyPostRepository extends JpaRepository<StudyPostEntity, Long>, StudyPostRepositoryCustom {

    @Query("SELECT new kr.co.studyhubinu.studyhubserver.studypost.dto.data.GetMyPostData(sp.id, sp.major, sp.title, sp.content, sp.remainingSeat, sp.close) " +
            "FROM StudyPostEntity sp " +
            "WHERE sp.postedUserId = :userId")
    Slice<GetMyPostData> findSliceByPostedUserId(Long userId, Pageable pageable);

    Long countByPostedUserId(Long userId);

    List<StudyPostEntity> findByPostedUserId(Long id);

//    List<RelatedPostData> findByMajor(MajorType major);
}
