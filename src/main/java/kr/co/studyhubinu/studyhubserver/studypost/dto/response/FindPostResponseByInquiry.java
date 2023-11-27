package kr.co.studyhubinu.studyhubserver.studypost.dto.response;

import kr.co.studyhubinu.studyhubserver.user.dto.data.UserData;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class FindPostResponseByInquiry {

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

    public FindPostResponseByInquiry(Long postId, MajorType major, String title, LocalDate studyStartDate, LocalDate studyEndDate, LocalDateTime createdDate, Integer studyPerson, GenderType filteredGender, Integer penalty, String penaltyWay, Integer remainingSeat, boolean close, boolean isBookmarked, UserData userData) {
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
}
