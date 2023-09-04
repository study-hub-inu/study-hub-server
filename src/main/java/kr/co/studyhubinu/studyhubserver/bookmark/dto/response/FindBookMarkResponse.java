package kr.co.studyhubinu.studyhubserver.bookmark.dto.response;

import lombok.Getter;

@Getter
public class FindBookMarkResponse {

    private Long postId;

    private String title;
    private String content;
    private int leftover;


    public FindBookMarkResponse(Long postId, String title, String content, int leftover) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.leftover = leftover;
    }

}
