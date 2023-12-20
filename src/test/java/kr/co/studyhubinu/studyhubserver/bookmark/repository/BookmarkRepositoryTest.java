package kr.co.studyhubinu.studyhubserver.bookmark.repository;

import kr.co.studyhubinu.studyhubserver.bookmark.domain.BookmarkEntity;
import kr.co.studyhubinu.studyhubserver.exception.bookmark.BookMarkNotFoundException;
import kr.co.studyhubinu.studyhubserver.support.fixture.BookmarkEntityFixture;
import kr.co.studyhubinu.studyhubserver.support.repository.RepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RepositoryTest
class BookmarkRepositoryTest {

    @Autowired
    private BookmarkRepository bookMarkRepository;

    @Test
    void 유저의_식별자로_북마크_리스트를_조회한다() {
        // given
        Long userId = 1L;
        BookmarkEntity bookmark1 = BookmarkEntityFixture.BOOKMARK_POST1.bookMarkEntity_생성(1L, userId);
        BookmarkEntity bookmark2 = BookmarkEntityFixture.BOOKMARK_POST2.bookMarkEntity_생성(2L, userId);
        bookMarkRepository.save(bookmark1);
        bookMarkRepository.save(bookmark2);

        // when
        List<BookmarkEntity> saveBookmark = bookMarkRepository.findByUserId(userId);

        // then
        assertAll(
                () -> assertThat(bookmark1.equals(saveBookmark.get(0))),
                () -> assertThat(bookmark2.equals(saveBookmark.get(1)))
        );
    }

    @Test
    void 유저의_식별자로_북마크_개수를_조회한다() {
        // given
        Long userId = 1L;
        BookmarkEntity bookmark1 = BookmarkEntityFixture.BOOKMARK_POST1.bookMarkEntity_생성(1L, userId);
        BookmarkEntity bookmark2 = BookmarkEntityFixture.BOOKMARK_POST2.bookMarkEntity_생성(2L, userId);
        bookMarkRepository.save(bookmark1);
        bookMarkRepository.save(bookmark2);

        // when
        Long actualCount = bookMarkRepository.countByUserId(userId);
        Long expectedCount = 2L;

        // then
        assertEquals(expectedCount, actualCount);
    }

    @Test
    void 유저와_게시글의_식별자로_북마크를_조회한다() {
        // given
        Long userId = 1L;
        Long postId = 3L;
        BookmarkEntity bookmark = BookmarkEntityFixture.BOOKMARK_POST1.bookMarkEntity_생성(postId, userId);
        bookMarkRepository.save(bookmark);

        // when
        BookmarkEntity findBookmarkByUserIdAndPostId = bookMarkRepository.findByUserIdAndPostId(userId, postId).orElseThrow(BookMarkNotFoundException::new);

        // then
        assertThat(bookmark.equals(findBookmarkByUserIdAndPostId));
    }
}
