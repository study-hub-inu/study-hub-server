package kr.co.studyhubinu.studyhubserver.studypost.dto.response;

import kr.co.studyhubinu.studyhubserver.study.enums.StudyWayType;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.RelatedPostData;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserData;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class FindPostResponseById {
    private Long postId;
    private String title;
    private LocalDateTime createdDate;
    private String content;
    private MajorType major;
    private int studyPerson;
    private GenderType filteredGender;
    private StudyWayType studyWay;
    private int penalty;
    private String penaltyWay;
    private LocalDate studyStartDate;
    private LocalDate studyEndDate;
    private int remainingSeat;
    private boolean isUsersPost;
    private boolean isBookmarked;
    private UserData postedUser;
    private RelatedPostData relatedPost;

    public FindPostResponseById(Long postId, String title, LocalDateTime createdDate, String content, MajorType major, int studyPerson, GenderType filteredGender, StudyWayType studyWay, int penalty, String penaltyWay, LocalDate studyStartDate, LocalDate studyEndDate, int remainingSeat, boolean isUsersPost, boolean isBookmarked, UserData postedUser, RelatedPostData relatedPost) {
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
        this.isUsersPost = isUsersPost;
        this.isBookmarked = isBookmarked;
        this.postedUser = postedUser;
        this.relatedPost = relatedPost;
    }
}
