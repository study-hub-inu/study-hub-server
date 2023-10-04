package kr.co.studyhubinu.studyhubserver.studyuser.domain;

import kr.co.studyhubinu.studyhubserver.study.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.*;

@Entity
//@IdClass()
public class StudyUserEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "post_id")
    private StudyPostEntity post;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
