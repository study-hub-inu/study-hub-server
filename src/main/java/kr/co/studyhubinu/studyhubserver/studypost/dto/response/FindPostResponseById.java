package kr.co.studyhubinu.studyhubserver.studypost.dto.response;

import kr.co.studyhubinu.studyhubserver.study.enums.StudyWayType;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.PostData;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.PostDataByMajor;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserData;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    private String chatUrl;
    private boolean isUsersPost;
    private boolean isBookmarked;
    private Long studyId;
    private UserData postedUser;
    private List<PostDataByMajor> relatedPost;

    public FindPostResponseById(PostData postData, List<PostDataByMajor> relatedPosts) {
        this.postId = postData.getPostId();
        this.title = postData.getTitle();
        this.createdDate = postData.getCreatedDate();
        this.content = postData.getContent();
        this.major = postData.getMajor();
        this.studyPerson = postData.getStudyPerson();
        this.filteredGender = postData.getFilteredGender();
        this.studyWay = postData.getStudyWay();
        this.penalty = postData.getPenalty();
        this.penaltyWay = postData.getPenaltyWay();
        this.studyStartDate = postData.getStudyStartDate();
        this.studyEndDate = postData.getStudyEndDate();
        this.remainingSeat = postData.getRemainingSeat();
        this.chatUrl = postData.getCharUrl();
        this.isUsersPost = postData.isUsersPost();
        this.isBookmarked = postData.isBookmarked();
        this.studyId = postData.getStudyId();
        this.postedUser = postData.getPostedUser();
        this.relatedPost = relatedPosts;
    }
}
