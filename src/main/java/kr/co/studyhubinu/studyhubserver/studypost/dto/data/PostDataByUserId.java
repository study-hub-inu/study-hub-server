package kr.co.studyhubinu.studyhubserver.studypost.dto.data;

import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Builder;
import lombok.Getter;


@Getter
public class PostDataByUserId {

    private final Long postId;
    private final MajorType major;
    private final String title;
    private final String content;
    private final Integer remainingSeat;
    private final boolean close;
    private final Long studyId;

    @Builder
    public PostDataByUserId(Long postId, MajorType major, String title, String content, Integer remainingSeat, boolean close, Long studyId) {
        this.postId = postId;
        this.major = major;
        this.title = title;
        this.content = content;
        this.remainingSeat = remainingSeat;
        this.close = close;
        this.studyId = studyId;
    }
}
