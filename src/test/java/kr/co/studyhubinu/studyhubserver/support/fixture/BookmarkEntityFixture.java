package kr.co.studyhubinu.studyhubserver.support.fixture;

import kr.co.studyhubinu.studyhubserver.bookmark.domain.BookmarkEntity;

public enum BookmarkEntityFixture {

    BOOKMARK_POST1(1L),
    BOOKMARK_POST2(2L);

    private final Long postId;

    BookmarkEntityFixture(Long postId) {
        this.postId = postId;
    }

    public BookmarkEntity bookMarkEntity_생성 (Long userId) {
        return BookmarkEntity.builder()
                .postId(this.postId)
                .userId(userId)
                .build();
    }
}
