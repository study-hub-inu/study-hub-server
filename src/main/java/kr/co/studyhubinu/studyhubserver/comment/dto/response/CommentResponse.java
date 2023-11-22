package kr.co.studyhubinu.studyhubserver.comment.dto.response;

import kr.co.studyhubinu.studyhubserver.user.dto.data.UserData;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {

    private Long commentId;
    private String content;
    private LocalDateTime createdDate;
    private boolean isUsersComment;
    private UserData commentedUserData;

    public CommentResponse(Long commentId, String content, LocalDateTime createdDate, boolean isUsersComment, UserData commentedUserData) {
        this.commentId = commentId;
        this.content = content;
        this.createdDate = createdDate;
        this.isUsersComment = isUsersComment;
        this.commentedUserData = commentedUserData;
    }
}
