package kr.co.studyhubinu.studyhubserver.comment.service;

import kr.co.studyhubinu.studyhubserver.comment.domain.CommentEntity;
import kr.co.studyhubinu.studyhubserver.comment.domain.CommentValidator;
import kr.co.studyhubinu.studyhubserver.comment.dto.request.UpdateCommentRequest;
import kr.co.studyhubinu.studyhubserver.comment.repository.CommentRepository;
import kr.co.studyhubinu.studyhubserver.studypost.repository.StudyPostRepository;
import kr.co.studyhubinu.studyhubserver.support.fixture.CommentEntityFixture;
import kr.co.studyhubinu.studyhubserver.support.fixture.UserEntityFixture;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private StudyPostRepository studyPostRepository;

    @Mock
    private CommentValidator commentValidator;

    @InjectMocks
    private CommentService commentService;

    @Test
    void 유저와_댓글의_식별자로_댓글을_수정한다() {
        // given
        Long commentId = 5L;
        Long commentPostId = 1L;
        Long commentUserId = 2L;
        String fixContent = "수정된 댓글 내용";
        UserEntity user = UserEntityFixture.DONGWOO.UserEntity_생성(commentUserId);
        CommentEntity comment = CommentEntityFixture.COMMENT_1.commentEntity_생성(commentUserId, commentPostId);
        given(userRepository.findById(anyLong())).willReturn(Optional.of(user));
        given(commentRepository.findById(anyLong())).willReturn(Optional.of(comment));
        UpdateCommentRequest request = new UpdateCommentRequest(commentId, fixContent);

        // when
        commentService.updateComment(request, commentUserId);

        // then
        assertAll(
                () -> assertThat(comment)
                        .extracting("userId",
                                "postId",
                                "content"
                        )
                        .containsExactly(commentUserId,
                                commentPostId,
                                fixContent
                        )
        );
    }





}
