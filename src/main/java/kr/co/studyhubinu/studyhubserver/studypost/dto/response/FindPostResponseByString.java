package kr.co.studyhubinu.studyhubserver.studypost.dto.response;

import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FindPostResponseByString {

    private Long postId;
    private MajorType major;
    private String title;
    private String content;
    private int leftover;
    private int studyPerson;
    private boolean close;

    public FindPostResponseByString(Long postId, MajorType major, String title, String content, int leftover, Integer studyPerson, Boolean close) {
        this.postId = postId;
        this.major = major;
        this.title = title;
        this.content = content;
        this.leftover = leftover;
        this.studyPerson = studyPerson;
        this.close = close;
    }

}
