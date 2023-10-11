package kr.co.studyhubinu.studyhubserver.study.dto.response;

import kr.co.studyhubinu.studyhubserver.study.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.study.enums.StudyWayType;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class FindPostResponseById {
    private Long postId;

    private String title;

    private String content;

    private String chatUrl;

    private MajorType major;

    private int studyPerson;

    private GenderType filteredGender;

    private StudyWayType studyWay;

    private int penalty;

    private LocalDate studyStartDate;

    private LocalDate studyEndDate;

    private Long postedUserId;

    private int remainingSeat;

    public FindPostResponseById(StudyPostEntity studyPost) {
        this.postId = studyPost.getId();
        this.title = studyPost.getTitle();
        this.content = studyPost.getContent();
        this.chatUrl = studyPost.getChatUrl();
        this.major = studyPost.getMajor();
        this.studyPerson = studyPost.getStudyPerson();
        this.filteredGender = studyPost.getFilteredGender();
        this.studyWay = studyPost.getStudyWay();
        this.penalty = studyPost.getPenalty();
        this.studyStartDate = studyPost.getStudyStartDate();
        this.studyEndDate = studyPost.getStudyEndDate();
        this.postedUserId = studyPost.getPostedUserId();
        this.remainingSeat = studyPost.getRemainingSeat();
    }

}
