package kr.co.studyhubinu.studyhubserver.userstudy.domain;

import kr.co.studyhubinu.studyhubserver.study.domain.StudyEntity;
import kr.co.studyhubinu.studyhubserver.study.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserStudyEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_post_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private StudyEntity study;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Builder
    public UserStudyEntity(StudyEntity study, UserEntity user) {
        this.study = study;
        this.user = user;
    }

}
