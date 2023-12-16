package kr.co.studyhubinu.studyhubserver.comment.repository;

import kr.co.studyhubinu.studyhubserver.comment.domain.CommentEntity;
import kr.co.studyhubinu.studyhubserver.comment.dto.response.CommentResponse;
import kr.co.studyhubinu.studyhubserver.support.fixture.CommentEntityFixture;
import kr.co.studyhubinu.studyhubserver.support.repository.RepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RepositoryTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void 게시글과_유저의_식별자로_댓글을_최신순으로_조회한다() throws InterruptedException {
        // given
        Long userId = 1L;
        Long postId = 3L;
        CommentEntity comment1 = CommentEntityFixture.COMMENT_1.commentEntity_생성(userId, postId);
        CommentEntity comment2 = CommentEntityFixture.COMMENT_2.commentEntity_생성(userId, postId);
        commentRepository.save(comment1);
        Thread.sleep(1000 * 3);
        commentRepository.save(comment2);

        // when
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createdDate"));
        Slice<CommentResponse> comments = commentRepository.findSliceByPostIdWithUserId(1L, userId, pageable);

        // then
        assertThat(comments.getContent()).hasSize(2);
        CommentResponse commentResponse1 = comments.getContent().get(1);
        CommentResponse commentResponse2 = comments.getContent().get(0);

        System.out.println("***********************" + commentResponse1.getContent() + commentResponse1.getCreatedDate().getNano());
        System.out.println("***********************" + commentResponse2.getContent() + commentResponse2.getCreatedDate().getNano());

        assertAll(
                () -> assertEquals(comment1.getId(), commentResponse1.getCommentId()),
                () -> assertEquals(comment1.getContent(), commentResponse1.getContent()),
                () -> assertEquals(comment2.getId(), commentResponse2.getCommentId()),
                () -> assertEquals(comment2.getContent(), commentResponse2.getContent())
        );
    }

}
