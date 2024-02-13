package kr.co.studyhubinu.studyhubserver.reject.repository;

import kr.co.studyhubinu.studyhubserver.reject.dto.response.RejectReasonResponse;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@RequiredArgsConstructor
public class RejectRepositoryImpl implements RejectRepositoryCustom {

    private final EntityManager em;

    @Override
    public RejectReasonResponse findByStudyIdAndRejectedUserId(Long studyId, Long rejectedUserId) {
        Query nativeQuery = em.createNativeQuery(makeNativeQuery(rejectedUserId));
        Object[] obj = (Object[]) nativeQuery.getSingleResult();

        return new RejectReasonResponse((String) obj[0], (String) obj[1]);
    }

    private String makeNativeQuery(Long rejectedUserId) {
        return "SELECT s.title, r.reject_reason " +
                "FROM (SELECT * FROM reject WHERE reject.rejected_user_id = " + rejectedUserId + ") as r " +
                "LEFT JOIN study as s ON s.study_id = r.study_id";
    }
}
