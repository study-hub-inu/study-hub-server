package kr.co.studyhubinu.studyhubserver.study.domain;

import kr.co.studyhubinu.studyhubserver.common.domain.BaseTimeEntity;
import kr.co.studyhubinu.studyhubserver.study.dto.data.UpdateStudyPostInfo;
import kr.co.studyhubinu.studyhubserver.study.enums.StudyWayType;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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

    private MajorType major;

    @Column(name = "study_person")
    private int studyPerson;

    @Column(name = "filtered_gender")
    private GenderType filteredGender;

    @Column(name = "study_way")
    private StudyWayType studyWay;

    private int penalty;

    @ColumnDefault("false")
    private boolean close;

    @Column(name = "study_start_date")
    private LocalDate studyStartDate;

    @Column(name = "study_end_date")
    private LocalDate studyEndDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    @Column(name = "remaining_seat")
    private int remainingSeat;

    @Builder
    public StudyPostEntity(String title, String content, String chatUrl, MajorType major, int studyPerson, GenderType filteredGender, StudyWayType studyWay, int penalty, LocalDate studyStartDate, LocalDate studyEndDate, UserEntity user, int remainingSeat) {
        this.title = title;
        this.content = content;
        this.chatUrl = chatUrl;
        this.major = major;
        this.studyPerson = studyPerson;
        this.filteredGender = filteredGender;
        this.studyWay = studyWay;
        this.penalty = penalty;
        this.studyStartDate = studyStartDate;
        this.studyEndDate = studyEndDate;
        this.user = user;
        this.remainingSeat = remainingSeat;
    }

    public void update(UpdateStudyPostInfo info) {
        this.title = info.getTitle();
        this.content = info.getContent();
        this.chatUrl = info.getChatUrl();
        this.major = info.getMajor();
        this.studyPerson = info.getStudyPerson();
        this.filteredGender = info.getGender();
        this.studyWay = info.getStudyWay();
        this.penalty = info.getPenalty();
        this.studyStartDate = info.getStartStartDate();
        this.studyEndDate = info.getStudyEndDate();
    }

    public boolean isVoteOfUser(Long userId) {
        return this.user.getId().equals(userId);
    }
}
