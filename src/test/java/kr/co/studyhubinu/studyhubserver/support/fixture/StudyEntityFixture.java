package kr.co.studyhubinu.studyhubserver.support.fixture;

import kr.co.studyhubinu.studyhubserver.study.domain.StudyEntity;

import java.time.LocalDate;

public enum StudyEntityFixture {

    INU("인천대학교 스터디", "반가워요 잘해봐요", LocalDate.of(2024, 1, 3), LocalDate.of(2024, 1, 5), "asdadsa", 1L);

    private String title;
    private String content;
    private LocalDate studyStartDate;
    private LocalDate studyEndDate;
    private String chatUrl;
    private Long userId;

    StudyEntityFixture(String title, String content, LocalDate studyStartDate, LocalDate studyEndDate, String chatUrl, Long userId) {
        this.title = title;
        this.content = content;
        this.studyStartDate = studyStartDate;
        this.studyEndDate = studyEndDate;
        this.chatUrl = chatUrl;
        this.userId = userId;
    }

    public StudyEntity studyEntity_생성() {
        return StudyEntity.builder()
                .title(title)
                .content(content)
                .studyStartDate(studyStartDate)
                .studyEndDate(studyEndDate)
                .chatUrl(chatUrl)
                .userId(userId)
                .build();
    }
}
