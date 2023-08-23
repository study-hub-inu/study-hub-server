package kr.co.studyhubinu.studyhubserver.user.domain;


import kr.co.studyhubinu.studyhubserver.study.domain.StudyPostEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JoinColumn(name = "study_post")
    @ManyToOne(fetch = LAZY)
    private StudyPostEntity studyPostEntity;

    @ManyToOne(fetch = LAZY)
    private UserEntity user;
}
