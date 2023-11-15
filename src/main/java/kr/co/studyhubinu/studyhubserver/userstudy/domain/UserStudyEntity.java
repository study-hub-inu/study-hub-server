package kr.co.studyhubinu.studyhubserver.userstudy.domain;

import kr.co.studyhubinu.studyhubserver.study.domain.StudyEntity;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_study")
public class UserStudyEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_post_id")
    private Long id;

    private boolean approve;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private StudyEntity study;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Builder
    public UserStudyEntity(boolean approve, StudyEntity study, UserEntity user) {
        this.approve = approve;
        this.study = study;
        this.user = user;
    }

}
