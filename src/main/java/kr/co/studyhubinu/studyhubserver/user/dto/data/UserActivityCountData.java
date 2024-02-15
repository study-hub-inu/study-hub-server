package kr.co.studyhubinu.studyhubserver.user.dto.data;

import lombok.Getter;

@Getter
public class UserActivityCountData {
    private final Long postCount;
    private final Long participateCount;
    private final Long applyCount;

    public UserActivityCountData(Long postCount, Long participateCount, Long applyCount) {
        this.postCount = postCount;
        this.participateCount = participateCount;
        this.applyCount = applyCount;
    }
}
