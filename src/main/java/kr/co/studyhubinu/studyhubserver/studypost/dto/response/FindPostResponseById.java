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
    private final String chatUrl;
    private final boolean isUsersPost;
    private final boolean isBookmarked;
    private final Long studyId;
    private final boolean isClose;
    private final boolean isApply;
    private final UserData postedUser;
    private final List<PostDataByMajor> relatedPost;

    public FindPostResponseById(PostData postData, List<PostDataByMajor> relatedPosts, boolean isApply) {
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
        this.isClose = postData.isClose();
        this.postedUser = postData.getPostedUser();
        this.relatedPost = relatedPosts;
        this.isApply = isApply;
    }
}
