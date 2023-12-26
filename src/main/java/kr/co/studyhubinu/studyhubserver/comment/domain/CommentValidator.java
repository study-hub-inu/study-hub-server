package kr.co.studyhubinu.studyhubserver.comment.domain;

import kr.co.studyhubinu.studyhubserver.exception.user.UserNotAccessRightException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentValidator {

    public void validIsCommentOfUser(Long userId, CommentEntity comment) {
        if (!comment.isCommentOfUser(userId)) throw new UserNotAccessRightException();
    }

}
