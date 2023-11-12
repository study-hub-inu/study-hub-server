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

    public PostData(Long postId, String title, LocalDateTime createdDate, String content, MajorType major, int studyPerson, GenderType filteredGender, StudyWayType studyWay, int penalty, String penaltyWay, LocalDate studyStartDate, LocalDate studyEndDate, int remainingSeat, boolean isUsersPost, boolean isBookmarked, UserData postedUser) {
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
    }

//    아이디ㅇ, 생성일ㅇ, 관련학과ㅇ, 제목ㅇ, 팀원수ㅇ, 벌금 종류ㅇ, 성별ㅇ, 내용ㅇ, 시작날짜ㅇ, 종료날짜ㅇ, 벌금ㅇ,
//    대면 여부ㅇ, 관련학과ㅇ, 작성자 아이디ㅇ, 작성자 학과d, 작성자 이름d, 작성자 프사d, 북마크 여부d, 이 글과 비슷한 스터디, 잔여석ㅇ, 작성자 여부ㅇ
}
