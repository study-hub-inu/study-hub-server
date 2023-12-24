package kr.co.studyhubinu.studyhubserver.comment.service;

import kr.co.studyhubinu.studyhubserver.comment.domain.CommentEntity;
import kr.co.studyhubinu.studyhubserver.comment.domain.CommentValidator;
import kr.co.studyhubinu.studyhubserver.comment.dto.request.CreateCommentRequest;
import kr.co.studyhubinu.studyhubserver.comment.dto.request.UpdateCommentRequest;
import kr.co.studyhubinu.studyhubserver.comment.repository.CommentRepository;
import kr.co.studyhubinu.studyhubserver.exception.StatusType;
import kr.co.studyhubinu.studyhubserver.exception.comment.CommentNotFoundException;
import kr.co.studyhubinu.studyhubserver.exception.study.PostNotFoundException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.studypost.repository.StudyPostRepository;
import kr.co.studyhubinu.studyhubserver.support.fixture.CommentEntityFixture;
import kr.co.studyhubinu.studyhubserver.support.fixture.UserEntityFixture;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

    @Test
    void 유저와_댓글의_식별자로_댓글을_삭제한다() {
        // given
        Long commentId = 5L;
        Long commentPostId = 1L;
        Long commentUserId = 2L;
        UserEntity user = UserEntityFixture.DONGWOO.UserEntity_생성(commentUserId);
        CommentEntity comment = CommentEntityFixture.COMMENT_1.commentEntity_생성(commentUserId, commentPostId);
        given(userRepository.findById(anyLong())).willReturn(Optional.of(user));
        given(commentRepository.findById(anyLong())).willReturn(Optional.of(comment));
        ArgumentCaptor<CommentEntity> captor = ArgumentCaptor.forClass(CommentEntity.class);

        // when
        commentService.deleteComment(commentId, commentUserId);

        // then
        verify(commentRepository, times(1)).delete(captor.capture());
        CommentEntity deletedComment = captor.getValue();
        assertAll(
                () -> assertEquals(deletedComment.getUserId(), commentUserId),
                () -> assertEquals(deletedComment.getPostId(), commentPostId),
                () -> assertEquals(deletedComment.getContent(), comment.getContent())
        );
    }

    @Test
    void 존재하지_않는_id로_게시글을_조회하면_PostNotFoundException_을_던진다() {
        // given
        Long commentPostId = 3L;
        String content = "안녕하세요!";
        given(studyPostRepository.findById(anyLong())).willReturn(Optional.empty());
        CreateCommentRequest request = new CreateCommentRequest(commentPostId, content);

        // when // then
        assertThatThrownBy(() ->  {
            commentService.createComment(request, commentPostId);
        }).isInstanceOf(PostNotFoundException.class)
                .extracting("status")
                .isEqualTo(StatusType.POST_NOT_FOUND);
    }

    @Test
    void 존재하지_않는_id로_댓글을_조회하면_CommentNotFoundException_을_던진다() {
        // given
        Long commentUserId = 1L;
        Long commentPostId = 3L;
        String updateContent = "안녕하세요!";
        UserEntity user = UserEntityFixture.DONGWOO.UserEntity_생성(commentUserId);
        given(userRepository.findById(anyLong())).willReturn(Optional.of(user));
        given(commentRepository.findById(anyLong())).willReturn(Optional.empty());
        UpdateCommentRequest request = new UpdateCommentRequest(commentPostId, updateContent);

        // when // then
        assertThatThrownBy(() ->  {
            commentService.updateComment(request, commentPostId);
        }).isInstanceOf(CommentNotFoundException.class)
                .extracting("status")
                .isEqualTo(StatusType.COMMENT_NOT_FOUND);
    }

    @Test
    void 존재하지_않는_id로_유저를_조회하면_UserNotFoundException_을_던진다() {
        // given
        Long commentUserId = 1L;
        Long commentPostId = 3L;
        String updateContent = "안녕하세요!";
        given(userRepository.findById(anyLong())).willReturn(Optional.empty());
        UpdateCommentRequest request = new UpdateCommentRequest(commentPostId, updateContent);

        // when // then
        assertThatThrownBy(() ->  {
            commentService.updateComment(request, commentPostId);
        }).isInstanceOf(UserNotFoundException.class)
                .extracting("status")
                .isEqualTo(StatusType.USER_NOT_FOUND);
    }
}
