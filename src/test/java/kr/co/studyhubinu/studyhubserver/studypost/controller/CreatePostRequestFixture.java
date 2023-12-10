package kr.co.studyhubinu.studyhubserver.studypost.controller;

import kr.co.studyhubinu.studyhubserver.study.enums.StudyWayType;
import kr.co.studyhubinu.studyhubserver.studypost.dto.request.CreatePostRequest;
import kr.co.studyhubinu.studyhubserver.studypost.dto.request.UpdatePostRequest;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;

import java.time.LocalDate;

public enum CreatePostRequestFixture {
    BLANK_TITLE("", "화이팅", "www.dfasdf", MajorType.COMPUTER_SCIENCE_ENGINEERING, 10, 10000, "지각비", false, GenderType.FEMALE, StudyWayType.CONTACT, LocalDate.parse("2023-08-23"), LocalDate.parse("2023-12-25")),
    BLANK_CONTENT("데이터 분석 스터디", "", "www.datastudy", MajorType.COMPUTER_SCIENCE_ENGINEERING, 5, 5000, "없음", false, GenderType.MALE, StudyWayType.UNTACT, LocalDate.parse("2023-09-10"), LocalDate.parse("2023-11-30")),
    BLANK_URL("데이터 분석 스터디", "화이팅", "", MajorType.COMPUTER_SCIENCE_ENGINEERING, 5, 5000, "없음", false, GenderType.MALE, StudyWayType.UNTACT, LocalDate.parse("2023-09-10"), LocalDate.parse("2023-11-30")),
    NULL_MAJOR("데이터 분석 스터디", "화이팅", "www.datastudy", null, 5, 5000, "없음", false, GenderType.MALE, StudyWayType.UNTACT, LocalDate.parse("2023-09-10"), LocalDate.parse("2023-11-30")),
    MIN_STUDY_PERSON("데이터 분석 스터디", "화이팅", "www.datastudy", MajorType.COMPUTER_SCIENCE_ENGINEERING, 1, 5000, "없음", false, GenderType.MALE, StudyWayType.UNTACT, LocalDate.parse("2023-09-10"), LocalDate.parse("2023-11-30")),
    NULL_GENDER("데이터 분석 스터디", "화이팅", "www.datastudy", MajorType.COMPUTER_SCIENCE_ENGINEERING, 5, 5000, "없음", false, null, StudyWayType.UNTACT, LocalDate.parse("2023-09-10"), LocalDate.parse("2023-11-30")),
    CORRECT_POST("데이터 분석 스터디", "화이팅", "www.datastudy", MajorType.COMPUTER_SCIENCE_ENGINEERING, 5, 5000, "없음", false, GenderType.MALE, StudyWayType.UNTACT, LocalDate.parse("2023-09-10"), LocalDate.parse("2023-11-30")),
    NULL_START("데이터 분석 스터디", "화이팅", "www.datastudy", MajorType.COMPUTER_SCIENCE_ENGINEERING, 5, 5000, "없음", false, GenderType.MALE, StudyWayType.UNTACT, null, LocalDate.parse("2023-11-30")),
    NULL_END("데이터 분석 스터디", "화이팅", "www.datastudy", MajorType.COMPUTER_SCIENCE_ENGINEERING, 5, 5000, "없음", false, GenderType.MALE, StudyWayType.UNTACT, LocalDate.parse("2023-09-10"), null);

    private final String title;
    private final String content;
    private final String chatUrl;
    private final MajorType major;
    private final Integer studyPerson;
    private final Integer penalty;
    private final String penaltyWay;
    private final boolean close;
    private final GenderType gender;
    private final StudyWayType studyWay;
    private final LocalDate studyStartDate;
    private final LocalDate studyEndDate;

    CreatePostRequestFixture(String title, String content, String chatUrl, MajorType major, Integer studyPerson, Integer penalty, String penaltyWay, boolean close, GenderType gender, StudyWayType studyWay, LocalDate studyStartDate, LocalDate studyEndDate) {
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

    public static CreatePostRequest createPostRequest_생성(CreatePostRequestFixture fixture) {
        return CreatePostRequest.builder()
                .title(fixture.getTitle())
                .content(fixture.getContent())
                .chatUrl(fixture.getChatUrl())
                .major(fixture.getMajor())
                .studyPerson(fixture.getStudyPerson())
                .penalty(fixture.getPenalty())
                .penaltyWay(fixture.getPenaltyWay())
                .close(fixture.isClose())
                .gender(fixture.getGender())
                .studyWay(fixture.getStudyWay())
                .studyStartDate(fixture.getStudyStartDate())
                .studyEndDate(fixture.getStudyEndDate())
                .build();
    }

    public static UpdatePostRequest updatePostRequest_생성(CreatePostRequestFixture fixture) {
        return UpdatePostRequest.builder()
                .postId(1L)
                .title(fixture.getTitle())
                .content(fixture.getContent())
                .chatUrl(fixture.getChatUrl())
                .major(fixture.getMajor())
                .studyPerson(fixture.getStudyPerson())
                .penalty(fixture.getPenalty())
                .penaltyWay(fixture.getPenaltyWay())
                .close(fixture.isClose())
                .gender(fixture.getGender())
                .studyWay(fixture.getStudyWay())
                .studyStartDate(fixture.getStudyStartDate())
                .studyEndDate(fixture.getStudyEndDate())
                .build();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getChatUrl() {
        return chatUrl;
    }

    public MajorType getMajor() {
        return major;
    }

    public Integer getStudyPerson() {
        return studyPerson;
    }

    public Integer getPenalty() {
        return penalty;
    }

    public String getPenaltyWay() {
        return penaltyWay;
    }

    public boolean isClose() {
        return close;
    }

    public GenderType getGender() {
        return gender;
    }

    public StudyWayType getStudyWay() {
        return studyWay;
    }

    public LocalDate getStudyStartDate() {
        return studyStartDate;
    }

    public LocalDate getStudyEndDate() {
        return studyEndDate;
    }
}
