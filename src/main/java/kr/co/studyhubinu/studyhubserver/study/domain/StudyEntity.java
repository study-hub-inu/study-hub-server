package kr.co.studyhubinu.studyhubserver.study.domain;

import kr.co.studyhubinu.studyhubserver.common.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "study")
public class StudyEntity extends BaseTimeEntity {

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

    @Column(name = "master_user_id")
    private Long masterUserId;



    @Builder
    public StudyEntity(Long id, String title, String content, LocalDate studyStartDate, LocalDate studyEndDate, String chatUrl, Long userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.studyStartDate = studyStartDate;
        this.studyEndDate = studyEndDate;
        this.chatUrl = chatUrl;
        this.masterUserId = userId;
    }

}
