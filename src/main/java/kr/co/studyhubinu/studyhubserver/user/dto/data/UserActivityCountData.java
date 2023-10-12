package kr.co.studyhubinu.studyhubserver.user.dto.data;

import lombok.Getter;

@Getter
public class UserActivityCountData {
    private Long postCount;
    private Long participateCount;
    private Long bookmarkCount;

    public UserActivityCountData(Long postCount, Long participateCount, Long bookmarkCount) {
        this.postCount = postCount;
        this.participateCount = participateCount;
        this.bookmarkCount = bookmarkCount;
    }
}
