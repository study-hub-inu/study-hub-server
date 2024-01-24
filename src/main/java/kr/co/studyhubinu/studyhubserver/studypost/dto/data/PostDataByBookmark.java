package kr.co.studyhubinu.studyhubserver.studypost.dto.data;

import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostDataByBookmark {
    private Long postId;
    private MajorType major;
    private String title;
    private String content;
    private int remainingSeat;
    private boolean close;
    private Long studyId;

    @Builder
    public PostDataByBookmark(Long postId, MajorType major, String title, String content, int remainingSeat, boolean close, Long studyId) {
        this.postId = postId;
        this.major = major;
        this.title = title;
        this.content = content;
        this.remainingSeat = remainingSeat;
        this.close = close;
        this.studyId = studyId;
    }
}
