package kr.co.studyhubinu.studyhubserver.reject.domain;

import kr.co.studyhubinu.studyhubserver.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "reject")
public class RejectEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reject_id")
    private Long id;

    @Column(name = "study_id")
    private Long studyId;

    @Column(name = "reject_reason")
    private String rejectReason;

    @Column(name = "rejected_user_id")
    private Long rejectedUserId;

    @Builder
    public RejectEntity(Long studyId, String rejectReason, Long rejectedUserId) {
        this.studyId = studyId;
        this.rejectReason = rejectReason;
        this.rejectedUserId = rejectedUserId;
    }
}
