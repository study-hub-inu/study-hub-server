package kr.co.studyhubinu.studyhubserver.studypost.dto.response;

import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Getter;

@Getter
public class FindPostResponseByBookMark {

    private Long postId;
    private MajorType major;
    private String title;
    private String content;
    private int leftover;
    private int studyPerson;
    private boolean close;
    private Long bookMarkCount;

    public FindPostResponseByBookMark(Long postId, MajorType major, String title, String content, int leftover, int studyPerson, Boolean close, Long bookMarkCount) {
        this.postId = postId;
        this.major = major;
        this.title = title;
        this.content = content;
        this.leftover = leftover;
        this.studyPerson = studyPerson;
        this.close = close;
        this.bookMarkCount = bookMarkCount;
    }
}
