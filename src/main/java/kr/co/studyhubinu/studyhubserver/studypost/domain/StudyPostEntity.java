package kr.co.studyhubinu.studyhubserver.studypost.domain;

import kr.co.studyhubinu.studyhubserver.common.domain.BaseTimeEntity;
import kr.co.studyhubinu.studyhubserver.exception.study.NoRemainingSeatsException;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.UpdateStudyPostInfo;
import kr.co.studyhubinu.studyhubserver.study.enums.StudyWayType;
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
@Table(name = "post")
public class StudyPostEntity extends BaseTimeEntity {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @Column(name = "chat_url")
    private String chatUrl;

    @Enumerated(EnumType.STRING)
    private MajorType major;

    @Column(name = "study_person")
    private Integer studyPerson;

    @Enumerated(EnumType.STRING)
    @Column(name = "filtered_gender")
    private GenderType filteredGender;

    @Enumerated(EnumType.STRING)
    @Column(name = "study_way")
    private StudyWayType studyWay;

    private Integer penalty;

    @Column(name = "penalty_way")
    private String penaltyWay;

    @ColumnDefault("false")
    private boolean close;

    @Column(name = "study_start_date")
    private LocalDate studyStartDate;

    @Column(name = "study_end_date")
    private LocalDate studyEndDate;

    @Column(name = "posted_user_id")
    private Long postedUserId;

    @Column(name = "remaining_seat")
    private Integer remainingSeat;

    @Column(name = "study_id")
    private Long studyId;

    @Builder
    public StudyPostEntity(Long id, String title, String content, String chatUrl, MajorType major, int studyPerson, GenderType filteredGender, StudyWayType studyWay, int penalty, String penaltyWay, boolean close, LocalDate studyStartDate, LocalDate studyEndDate, Long postedUserId, int remainingSeat, Long studyId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.chatUrl = chatUrl;
        this.major = major;
        this.studyPerson = studyPerson;
        this.filteredGender = filteredGender;
        this.studyWay = studyWay;
        this.penalty = penalty;
        this.penaltyWay = penaltyWay;
        this.close = close;
        this.studyStartDate = studyStartDate;
        this.studyEndDate = studyEndDate;
        this.postedUserId = postedUserId;
        this.remainingSeat = remainingSeat;
        this.studyId = studyId;
    }

    public void update(UpdateStudyPostInfo info, int currentJoinCount) {
        this.title = info.getTitle();
        this.content = info.getContent();
        this.chatUrl = info.getChatUrl();
        this.major = info.getMajor();
        this.studyPerson = info.getStudyPerson();
        this.filteredGender = info.getGender();
        this.studyWay = info.getStudyWay();
        this.penalty = info.getPenalty();
        this.penaltyWay = info.getPenaltyWay();
        this.close = info.isClose();
        this.studyStartDate = info.getStudyStartDate();
        this.studyEndDate = info.getStudyEndDate();
        this.remainingSeat = info.getStudyPerson() - currentJoinCount;
    }

    public boolean isPostOfUser(Long userId) {
        return this.postedUserId.equals(userId);
    }

    public void close() {
        this.close = true;
    }

    public void decreaseRemainingSeat() {
        if (this.remainingSeat - 1 < 0) {
            throw new NoRemainingSeatsException();
        }
        this.remainingSeat -= 1;

    }

    public void closeStudyPostIfRemainingSeatIsZero() {
        if (this.remainingSeat == 0) {
            this.close = true;
        }
    }
}
