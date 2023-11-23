package kr.co.studyhubinu.studyhubserver.support.fixture;

import kr.co.studyhubinu.studyhubserver.study.enums.StudyWayType;
import kr.co.studyhubinu.studyhubserver.studypost.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;

import java.time.LocalDate;

public enum StudyPostEntityFixture {

    SQLD("SQLD딸사람 구해요", "열심히 할 사람만요", MajorType.COMPUTER_SCIENCE_ENGINEERING, 5, GenderType.MALE, StudyWayType.MIX, 10000, "지각비", LocalDate.of(2023, 10, 25), LocalDate.of(2023, 12, 25), 10);

    private final String title;
    private final String content;
    private final MajorType major;
    private final Integer studyPerson;
    private final GenderType gender;
    private final StudyWayType studyWay;
    private final Integer penalty;
    private final String penaltyWay;
    private final LocalDate studyStartDate;
    private final LocalDate studyEndDate;
    private final Integer remainingSeat;

    StudyPostEntityFixture(String title, String content, MajorType major, Integer studyPerson, GenderType gender, StudyWayType studyWay, Integer penalty, String penaltyWay, LocalDate studyStartDate, LocalDate studyEndDate, Integer remainingSeat) {
        this.title = title;
        this.content = content;
        this.major = major;
        this.studyPerson = studyPerson;
        this.gender = gender;
        this.studyWay = studyWay;
        this.penalty = penalty;
        this.penaltyWay = penaltyWay;
        this.studyStartDate = studyStartDate;
        this.studyEndDate = studyEndDate;
        this.remainingSeat = remainingSeat;
    }

    public StudyPostEntity studyPostEntity_생성(Long id) {
        return StudyPostEntity.builder()
                .id(id)
                .title(this.title)
                .content(this.content)
                .major(this.major)
                .studyPerson(this.studyPerson)
                .filteredGender(this.gender)
                .studyWay(this.studyWay)
                .penalty(this.penalty)
                .penaltyWay(this.penaltyWay)
                .studyStartDate(this.studyStartDate)
                .studyEndDate(this.studyEndDate)
                .remainingSeat(this.remainingSeat)
                .build();
    }
}