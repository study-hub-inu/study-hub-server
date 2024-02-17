package kr.co.studyhubinu.studyhubserver.reject.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.studyhubinu.studyhubserver.reject.dto.response.RejectReasonResponse;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

import static kr.co.studyhubinu.studyhubserver.apply.domain.QRejectEntity.rejectEntity;
import static kr.co.studyhubinu.studyhubserver.study.domain.QStudyEntity.studyEntity;

@RequiredArgsConstructor
public class RejectRepositoryImpl implements RejectRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public RejectReasonResponse findByStudyIdAndRejectedUserId(Long studyId, Long rejectedUserId) {
        return jpaQueryFactory
                .select(Projections.constructor(RejectReasonResponse.class,
                        studyEntity.title, rejectEntity.rejectReason))
                .from(rejectEntity)
                .innerJoin(studyEntity)
                .on(rejectEntity.studyId.eq(studyEntity.id))
                .where(rejectEntity.studyId.eq(studyId), rejectEntity.rejectedUserId.eq(rejectedUserId))
                .fetchOne();
    }

//    @Override
//    public RejectReasonResponse findByStudyIdAndRejectedUserId(Long studyId, Long rejectedUserId) {
//        Query nativeQuery = em.createNativeQuery(makeNativeQuery(rejectedUserId));
//        Object[] obj = (Object[]) nativeQuery.getSingleResult();
//
//        return new RejectReasonResponse((String) obj[0], (String) obj[1]);
//    }

//    private String makeNativeQuery(Long rejectedUserId) {
//        return "SELECT s.title, r.reject_reason " +
//                "FROM (SELECT * FROM reject WHERE reject.rejected_user_id = " + rejectedUserId + ") as r " +
//                "LEFT JOIN study as s ON s.study_id = r.study_id";
//    }
}
