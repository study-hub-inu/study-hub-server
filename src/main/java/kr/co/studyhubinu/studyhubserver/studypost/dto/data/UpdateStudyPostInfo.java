package kr.co.studyhubinu.studyhubserver.studypost.dto.data;

import kr.co.studyhubinu.studyhubserver.study.enums.StudyWayType;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UpdateStudyPostInfo {
    private final Long postId;
    private final Long userId;
    private final String title;
    private final String content;
    private final String chatUrl;
    private final MajorType major;
    private final int studyPerson;
    private final int penalty;
    private final String penaltyWay;
    private final boolean close;
    private final GenderType gender;
    private final StudyWayType studyWay;
    private final LocalDate studyStartDate;
    private final LocalDate studyEndDate;

    @Builder
    public UpdateStudyPostInfo(Long postId, Long userId, String title, String content, String chatUrl, MajorType major, int studyPerson, int penalty, String penaltyWay, boolean close, GenderType gender, StudyWayType studyWay, LocalDate studyStartDate, LocalDate studyEndDate) {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.chatUrl = chatUrl;
        this.major = major;
        this.studyPerson = studyPerson;
        this.penalty = penalty;
        this.penaltyWay = penaltyWay;
        this.close = close;
        this.gender = gender;
        this.studyWay = studyWay;
        this.studyStartDate = studyStartDate;
        this.studyEndDate = studyEndDate;
    }
}
