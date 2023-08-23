package kr.co.studyhubinu.studyhubserver.study.dto.data;

import kr.co.studyhubinu.studyhubserver.study.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.study.enums.StudyWayType;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class StudyPostInfo {

    private Long userId;
    private String title;
    private String content;
    private String chatUrl;
    private int studyPerson;
    private int penalty;
    private GenderType gender;
    private StudyWayType studyWay;
    private LocalDate startStartDate;
    private LocalDate studyEndDate;
    private List<String> interests;

    @Builder
    public StudyPostInfo(Long userId, String title, String content, String chatUrl, int studyPerson, int penalty, GenderType gender, StudyWayType studyWay, LocalDate startStartDate, LocalDate studyEndDate, List<String> interests) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.chatUrl = chatUrl;
        this.studyPerson = studyPerson;
        this.penalty = penalty;
        this.gender = gender;
        this.studyWay = studyWay;
        this.startStartDate = startStartDate;
        this.studyEndDate = studyEndDate;
        this.interests = interests;
    }

    public StudyPostEntity toEntity(UserEntity user) {

        return StudyPostEntity.builder()
                .title(title)
                .content(content)
                .chatUrl(chatUrl)
                .studyPerson(studyPerson)
                .filteredGender(gender)
                .studyWay(studyWay)
                .penalty(penalty)
                .studyStartDate(startStartDate)
                .studyEndDate(studyEndDate)
                .user(user)
                .build();

    }
}
