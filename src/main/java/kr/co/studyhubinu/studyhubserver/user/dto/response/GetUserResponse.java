package kr.co.studyhubinu.studyhubserver.user.dto.response;

import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserActivityCountData;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetUserResponse {

    private Long postCount;
    private Long participateCount;
    private Long bookmarkCount;
    private String nickname;
    private MajorType major;
    private GenderType gender;
    private String email;
    private String imageUrl;

    public GetUserResponse(UserEntity user, UserActivityCountData data) {
        this.postCount = data.getPostCount();
        this.participateCount = data.getParticipateCount();
        this.bookmarkCount = data.getBookmarkCount();
        this.nickname = user.getNickname();
        this.major = user.getMajor();
        this.gender = user.getGender();
        this.email = user.getEmail();
        this.imageUrl = user.getImageUrl();
    }

    @Builder
    public GetUserResponse(Long postCount, Long participateCount, Long bookmarkCount, String nickname, MajorType major, GenderType gender, String email, String imageUrl) {
        this.postCount = postCount;
        this.participateCount = participateCount;
        this.bookmarkCount = bookmarkCount;
        this.nickname = nickname;
        this.major = major;
        this.gender = gender;
        this.email = email;
        this.imageUrl = imageUrl;
    }
}
