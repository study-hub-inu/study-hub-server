package kr.co.studyhubinu.studyhubserver.studypost.dto.data;

import kr.co.studyhubinu.studyhubserver.study.enums.StudyWayType;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserData;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class PostData {
    private final Long postId;
    private final String title;
    private final LocalDateTime createdDate;
    private final String content;
    private final MajorType major;
    private final int studyPerson;
    private final GenderType filteredGender;
    private final StudyWayType studyWay;
    private final int penalty;
    private final String penaltyWay;
    private final LocalDate studyStartDate;
    private final LocalDate studyEndDate;
    private final int remainingSeat;
    private final String charUrl;
    private final boolean isUsersPost;
    private final boolean isBookmarked;
    private final UserData postedUser;

    public PostData(Long postId, String title, LocalDateTime createdDate, String content, MajorType major, int studyPerson, GenderType filteredGender, StudyWayType studyWay, int penalty, String penaltyWay, LocalDate studyStartDate, LocalDate studyEndDate, int remainingSeat, String charUrl, boolean isUsersPost, boolean isBookmarked, UserData postedUser) {
        this.postId = postId;
        this.title = title;
        this.createdDate = createdDate;
        this.content = content;
        this.major = major;
        this.studyPerson = studyPerson;
        this.filteredGender = filteredGender;
        this.studyWay = studyWay;
        this.penalty = penalty;
        this.penaltyWay = penaltyWay;
        this.studyStartDate = studyStartDate;
        this.studyEndDate = studyEndDate;
        this.remainingSeat = remainingSeat;
        this.charUrl = charUrl;
        this.isUsersPost = isUsersPost;
        this.isBookmarked = isBookmarked;
        this.postedUser = postedUser;
    }
}
