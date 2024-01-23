package kr.co.studyhubinu.studyhubserver.comment.repository;

import kr.co.studyhubinu.studyhubserver.comment.dto.response.CommentResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentRepositoryCustom {
    List<CommentResponse> findSliceByPostIdWithUserId(Long postId, Long userId, Pageable pageable);

    List<CommentResponse> findPreviewByPostId(Long postId, Long userId, Long commentPreviewCount);
}
