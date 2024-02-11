package kr.co.studyhubinu.studyhubserver.bookmark.repository;

import kr.co.studyhubinu.studyhubserver.bookmark.domain.BookmarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long>, BookmarkRepositoryCustom {
    Optional<BookmarkEntity> findByUserIdAndPostId(Long userId, Long postId);

    Long countByUserId(Long userId);

    List<BookmarkEntity> findByUserId(Long id);
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM BookmarkEntity bm WHERE bm.userId = :userId")
    void deleteAllBookmarksByUserId(@Param("userId") Long userId);
}
