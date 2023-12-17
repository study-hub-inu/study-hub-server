package kr.co.studyhubinu.studyhubserver.studypost.dto.data;

import kr.co.studyhubinu.studyhubserver.study.enums.StudyWayType;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserData;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class IntegratedPostData {

    private Long postId;
    private MajorType major;
    private String title;
    private LocalDate studyStartDate;
    private LocalDate studyEndDate;
    private LocalDateTime createdDate;
    private Integer studyPerson;
    private GenderType filteredGender;
    private Integer penalty;
    private String penaltyWay;
    private Integer remainingSeat;
    private boolean close;
    private boolean isBookmarked;
    private UserData userData;
    private String content;
    private Long postedUserId;
    private String chatUrl;
    private StudyWayType studyWay;
    private Long userId;


    public IntegratedPostData(Long postId, MajorType major, String title, LocalDate studyStartDate, LocalDate studyEndDate, LocalDateTime createdDate, Integer studyPerson, GenderType filteredGender, Integer penalty, String penaltyWay, Integer remainingSeat, boolean close, boolean isBookmarked, UserData userData) {
        this.postId = postId;
        this.major = major;
        this.title = title;
        this.studyStartDate = studyStartDate;
        this.studyEndDate = studyEndDate;
        this.createdDate = createdDate;
        this.studyPerson = studyPerson;
        this.filteredGender = filteredGender;
        this.penalty = penalty;
        this.penaltyWay = penaltyWay;
        this.remainingSeat = remainingSeat;
        this.close = close;
        this.isBookmarked = isBookmarked;
        this.userData = userData;
    }

    public IntegratedPostData(Long postId, MajorType major, String title, String content, Integer remainingSeat, boolean close) {
        this.postId = postId;
        this.major = major;
        this.title = title;
        this.content = content;
        this.remainingSeat = remainingSeat;
        this.close = close;
    }

    public IntegratedPostData(Long postId, MajorType major, String title, LocalDate studyStartDate, LocalDate studyEndDate, LocalDateTime createdDate, Integer studyPerson,
                              GenderType filteredGender, Integer penalty, String penaltyWay,
                              Integer remainingSeat, UserData userData, String content, Long postedUserId, String chatUrl, StudyWayType studyWay, Long userId) {
        this.postId = postId;
        this.major = major;
        this.title = title;
        this.studyStartDate = studyStartDate;
        this.studyEndDate = studyEndDate;
        this.createdDate = createdDate;
        this.studyPerson = studyPerson;
        this.filteredGender = filteredGender;
        this.penalty = penalty;
        this.penaltyWay = penaltyWay;
        this.remainingSeat = remainingSeat;
        this.userData = userData;
        this.content = content;
        this.postedUserId = postedUserId;
        this.chatUrl = chatUrl;
        this.studyWay = studyWay;
        this.userId = userId;
    }

    public IntegratedPostData(Long postId, String title, MajorType major, Integer remainingSeat, UserData userData) {
        this.postId = postId;
        this.major = major;
        this.title = title;
        this.remainingSeat = remainingSeat;
        this.userData = userData;
    }
}
