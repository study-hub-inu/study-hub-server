package kr.co.studyhubinu.studyhubserver.apply.domain;

import kr.co.studyhubinu.studyhubserver.apply.enums.Inspection;
import kr.co.studyhubinu.studyhubserver.study.domain.StudyEntity;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "apply")
public class ApplyEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "apply_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Inspection inspection;

    @ManyToOne
    @JoinColumn(name = "study_id")
    private StudyEntity study;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Builder
    public ApplyEntity(StudyEntity study, UserEntity user) {
        this.study = study;
        this.user = user;
    }

}
