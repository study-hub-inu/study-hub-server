package kr.co.studyhubinu.studyhubserver.studypost.dto.data;

import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Getter;

@Getter
public class GetMyPostData {
    private Long postId;
    private MajorType major;
    private String title;
    private String content;
    private int remainingSeat;
    private boolean close;

    public GetMyPostData(Long postId, MajorType major, String title, String content, int remainingSeat, boolean close) {
        this.postId = postId;
        this.major = major;
        this.title = title;
        this.content = content;
        this.remainingSeat = remainingSeat;
        this.close = close;
    }
}
