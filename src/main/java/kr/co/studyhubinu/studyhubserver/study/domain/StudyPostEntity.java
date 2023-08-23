package kr.co.studyhubinu.studyhubserver.study.domain;

import kr.co.studyhubinu.studyhubserver.common.domain.BaseTimeEntity;
import kr.co.studyhubinu.studyhubserver.study.enums.StudyWayType;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "POST")
public class StudyPostEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @Column(name = "chat_url")
    private String chatUrl;

    @Column(name = "study_person")
    private int studyPerson;

    @Column(name = "filtered_gender")
    private GenderType filteredGender;

    @Column(name = "study_way")
    private StudyWayType studyWay;

    private int penalty;

    @Column(name = "study_start_date")
    private LocalDate studyStartDate;

    @Column(name = "study_end_date")
    private LocalDate studyEndDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    @Builder
    public StudyPostEntity(String title, String content, String chatUrl, int studyPerson, GenderType filteredGender, StudyWayType studyWay, int penalty, LocalDate studyStartDate, LocalDate studyEndDate, UserEntity user) {
        this.title = title;
        this.content = content;
        this.chatUrl = chatUrl;
        this.studyPerson = studyPerson;
        this.filteredGender = filteredGender;
        this.studyWay = studyWay;
        this.penalty = penalty;
        this.studyStartDate = studyStartDate;
        this.studyEndDate = studyEndDate;
        this.user = user;
    }
}
