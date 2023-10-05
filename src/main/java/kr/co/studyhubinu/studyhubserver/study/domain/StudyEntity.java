package kr.co.studyhubinu.studyhubserver.study.domain;

import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.userstudy.domain.UserStudyEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "study")
public class StudyEntity {

    @Id
    @Column(name = "study_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @Column(name = "study_start_date")
    private LocalDate studyStartDate;

    @Column(name = "study_end_date")
    private LocalDate studyEndDate;

    @Column(name = "chat_url")
    private String chatUrl;

    @Column(name = "user_id")
    private Long userId;

    @OneToMany(mappedBy = "study")
    private List<UserStudyEntity> userStudyEntityList;


    @Builder
    public StudyEntity(String title, String content, LocalDate studyStartDate, LocalDate studyEndDate, String chatUrl) {
        this.title = title;
        this.content = content;
        this.studyStartDate = studyStartDate;
        this.studyEndDate = studyEndDate;
        this.chatUrl = chatUrl;
    }

}
