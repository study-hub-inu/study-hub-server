package kr.co.studyhubinu.studyhubserver.studypost.dto.data;

import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Builder;
import lombok.Getter;


@Getter
public class PostDataByUserId {

    private Long postId;
    private MajorType major;
    private String title;
    private String content;
    private Integer remainingSeat;
    private boolean close;

    @Builder
    public PostDataByUserId(Long postId, MajorType major, String title, String content, Integer remainingSeat, boolean close) {
        this.postId = postId;
        this.major = major;
        this.title = title;
        this.content = content;
        this.remainingSeat = remainingSeat;
        this.close = close;
    }
}
