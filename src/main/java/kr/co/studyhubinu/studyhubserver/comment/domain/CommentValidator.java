package kr.co.studyhubinu.studyhubserver.comment.domain;

import kr.co.studyhubinu.studyhubserver.exception.user.UserNotAccessRightException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommentValidator {

    public void validIsCommentOfUser(Long userId, CommentEntity comment) {
        log.info("*****************" + userId);
        log.info("%%%%%%%%%%%%%%%%%" + comment.getUserId());
        if (!comment.isCommentOfUser(userId)) throw new UserNotAccessRightException();
    }

}
