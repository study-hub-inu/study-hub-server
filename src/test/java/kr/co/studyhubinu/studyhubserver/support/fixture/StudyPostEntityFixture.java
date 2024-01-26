package kr.co.studyhubinu.studyhubserver.support.fixture;

import kr.co.studyhubinu.studyhubserver.study.enums.StudyWayType;
import kr.co.studyhubinu.studyhubserver.studypost.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;

import java.time.LocalDate;

public enum StudyPostEntityFixture {

    SQLD("SQLD딸사람 구해요", "열심히 할 사람만요", MajorType.COMPUTER_SCIENCE_ENGINEERING, 5, GenderType.MALE, StudyWayType.MIX, 10000, "지각비", LocalDate.of(2023, 10, 25), LocalDate.of(2023, 12, 25), 5, 5L),
    ENGINEER_INFORMATION_PROCESSING("정처기 딸사람 구해요", "심장을 바칠 사람만요", MajorType.COMPUTER_SCIENCE_ENGINEERING, 8, GenderType.FEMALE, StudyWayType.MIX, 5000, "지각비", LocalDate.of(2023, 10, 25), LocalDate.of(2024, 2, 13), 8, 7L),
    TOEIC("토익 딸사람 구해요", "800점 이상 목표인 사람들만", MajorType.NONE, 3, GenderType.NULL, StudyWayType.MIX, 50000, "800 달성 실패시", LocalDate.of(2023, 4, 30), LocalDate.of(2023, 8, 25), 3, 8L),
    ENGINEER_INFORMATION_PROCESSING_WITH_MALE("정처기 딸사람!!", "남자만요", MajorType.COMPUTER_SCIENCE_ENGINEERING, 8, GenderType.MALE, StudyWayType.MIX, 5000, "지각비", LocalDate.of(2023, 10, 25), LocalDate.of(2024, 2, 13), 8, 10L);


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
    private Long studyId;

    StudyPostEntityFixture(String title, String content, MajorType major, Integer studyPerson, GenderType gender, StudyWayType studyWay, Integer penalty, String penaltyWay, LocalDate studyStartDate, LocalDate studyEndDate, Integer remainingSeat, Long studyId) {
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
        this.studyId = studyId;
    }

    public StudyPostEntity studyPostEntity_생성(Long userId) {
        return StudyPostEntity.builder()
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
                .postedUserId(userId)
                .remainingSeat(this.remainingSeat)
                .studyId(this.studyId)
                .build();
    }

    public StudyPostEntity studyPostEntity_생성(Long userId, Long postId) {
        return StudyPostEntity.builder()
                .id(postId)
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
                .postedUserId(userId)
                .remainingSeat(this.remainingSeat)
                .studyId(this.studyId)
                .build();
    }

    public StudyPostEntity studyPostEntity_생성_studyId_추가(Long userId, Long postId, Long studyId) {
        return StudyPostEntity.builder()
                .id(postId)
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
                .postedUserId(userId)
                .remainingSeat(this.remainingSeat)
                .studyId(studyId)
                .build();
    }

    public void setStudyId(Long studyId) {
        this.studyId = studyId;
    }
}
