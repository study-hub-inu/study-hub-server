package kr.co.studyhubinu.studyhubserver.study.dto.response;

import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Getter;

@Getter
public class GetMyPostResponse {
    private MajorType major;
    private String title;
    private String content;
    private int remainingSeat;
    private boolean close;

    public GetMyPostResponse(MajorType major, String title, String content, int remainingSeat, boolean close) {
        this.major = major;
        this.title = title;
        this.content = content;
        this.remainingSeat = remainingSeat;
        this.close = close;
    }
}
