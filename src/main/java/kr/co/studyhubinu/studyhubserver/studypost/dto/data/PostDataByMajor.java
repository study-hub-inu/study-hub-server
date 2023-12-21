package kr.co.studyhubinu.studyhubserver.studypost.dto.data;

import kr.co.studyhubinu.studyhubserver.user.dto.data.UserData;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostDataByMajor {
    private final Long postId;
    private final String title;
    private final MajorType major;
    private final int remainingSeat;
    private final UserData userData;

    @Builder
    public PostDataByMajor(Long postId, String title, MajorType major, int remainingSeat, UserData userData) {
        this.postId = postId;
        this.title = title;
        this.major = major;
        this.remainingSeat = remainingSeat;
        this.userData = userData;
    }
}
