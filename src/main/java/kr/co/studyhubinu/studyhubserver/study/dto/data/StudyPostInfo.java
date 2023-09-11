package kr.co.studyhubinu.studyhubserver.study.dto.data;

import kr.co.studyhubinu.studyhubserver.study.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.study.enums.StudyWayType;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class StudyPostInfo {

    private Long userId;
    private String title;
    private String content;
    private String chatUrl;
    private MajorType major;
    private int studyPerson;
    private int penalty;
    private GenderType gender;
    private StudyWayType studyWay;
    private LocalDate studyStartDate;
    private LocalDate studyEndDate;

    @Builder
    public StudyPostInfo(Long userId, String title, String content, String chatUrl, MajorType major, int studyPerson, int penalty, GenderType gender, StudyWayType studyWay, LocalDate studyStartDate, LocalDate studyEndDate) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.chatUrl = chatUrl;
        this.major = major;
        this.studyPerson = studyPerson;
        this.penalty = penalty;
        this.gender = gender;
        this.studyWay = studyWay;
        this.studyStartDate = studyStartDate;
        this.studyEndDate = studyEndDate;
    }

    public StudyPostEntity toEntity(UserEntity user) {

        return StudyPostEntity.builder()
                .title(title)
                .content(content)
                .chatUrl(chatUrl)
                .major(major)
                .studyPerson(studyPerson)
                .filteredGender(gender)
                .studyWay(studyWay)
                .penalty(penalty)
                .studyStartDate(studyStartDate)
                .studyEndDate(studyEndDate)
                .user(user)
                .build();

    }
}
