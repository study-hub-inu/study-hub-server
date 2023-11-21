package kr.co.studyhubinu.studyhubserver.comment.repository;

import kr.co.studyhubinu.studyhubserver.comment.dto.response.CommentResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface CommentRepositoryCustom {
    Slice<CommentResponse> findSliceByPostIdWithUserId(Long postId, Long userId, Pageable pageable);
}
