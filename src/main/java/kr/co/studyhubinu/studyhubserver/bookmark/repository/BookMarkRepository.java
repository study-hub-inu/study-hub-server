package kr.co.studyhubinu.studyhubserver.bookmark.repository;

import kr.co.studyhubinu.studyhubserver.bookmark.domain.BookMarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BookMarkRepository extends JpaRepository<BookMarkEntity, Long>, BookMarkRepositoryCustom {
    Optional<BookMarkEntity> findByUserIdAndPostId(Long userId, Long postId);
}
