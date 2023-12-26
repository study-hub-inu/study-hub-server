package kr.co.studyhubinu.studyhubserver.comment;

import kr.co.studyhubinu.studyhubserver.comment.domain.CommentEntity;
import kr.co.studyhubinu.studyhubserver.comment.domain.CommentValidator;
import kr.co.studyhubinu.studyhubserver.exception.StatusType;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotAccessRightException;
import kr.co.studyhubinu.studyhubserver.support.fixture.CommentEntityFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class CommentValidatorTest {

    @InjectMocks
    private CommentValidator commentValidator;

    @Test
    void 댓글을_작성한_유저와_다른_유저의_식별자로_댓글에_접근하면_UserNotAccessRightException_을_던진다() {
        // given
        Long commentPostId = 1L;
        Long commentUserId = 2L;
        Long anotherUserId = 9L;
        CommentEntity comment = CommentEntityFixture.COMMENT_1.commentEntity_생성(commentUserId, commentPostId);

        // when // then
        assertThatThrownBy(() ->  {
            commentValidator.validIsCommentOfUser(anotherUserId, comment);
        }).isInstanceOf(UserNotAccessRightException.class)
                .extracting("status")
                .isEqualTo(StatusType.ACCESS_RIGHT_FAILED);
    }

}
