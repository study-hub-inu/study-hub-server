package kr.co.studyhubinu.studyhubserver.comment.repository;

import kr.co.studyhubinu.studyhubserver.comment.domain.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
