package kr.co.studyhubinu.studyhubserver.support.fixture;

import kr.co.studyhubinu.studyhubserver.bookmark.domain.BookmarkEntity;

public enum BookmarkEntityFixture {

    BOOKMARK_POST1_USER1(1L, 1L),
    BOOKMARK_POST2_USER1(2L, 1L);

    private final Long postId;
    private final Long userId;

    BookmarkEntityFixture(Long postId, Long userId) {
        this.postId = postId;
        this.userId = userId;
    }

    public BookmarkEntity bookMarkEntity_생성 (Long bookmarkId) {
        return BookmarkEntity.builder()
                .id(bookmarkId)
                .postId(this.postId)
                .userId(this.userId)
                .build();
    }
}
