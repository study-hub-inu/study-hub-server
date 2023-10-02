package kr.co.studyhubinu.studyhubserver.userpost.domain;

import kr.co.studyhubinu.studyhubserver.study.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserPostEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_post_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private StudyPostEntity studyPostEntity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Builder
    public UserPostEntity(StudyPostEntity studyPost, UserEntity user) {
        this.studyPostEntity = studyPost;
        this.user = user;
    }

}
