package kr.co.studyhubinu.studyhubserver.apply.domain;

import kr.co.studyhubinu.studyhubserver.apply.enums.Inspection;
import kr.co.studyhubinu.studyhubserver.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "apply")
public class ApplyEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "apply_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Inspection inspection;

    private String introduce;

    @Column(name = "study_id")
    private Long studyId;

    @Column(name = "user_id")
    private Long userId;

    @Builder
    public ApplyEntity(Inspection inspection, String introduce, Long studyId, Long userId) {
        this.inspection = inspection;
        this.introduce = introduce;
        this.studyId = studyId;
        this.userId = userId;
    }

    public static ApplyEntity of(Long userId, Long studyId, String introduce) {
        return ApplyEntity.builder()
                .userId(userId)
                .studyId(studyId)
                .introduce(introduce)
                .inspection(Inspection.STANDBY)
                .build();
    }

    public void update(Inspection inspection) {
        this.inspection = inspection;
    }
}
