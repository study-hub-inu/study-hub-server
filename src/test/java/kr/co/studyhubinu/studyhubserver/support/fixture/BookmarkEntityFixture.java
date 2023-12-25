package kr.co.studyhubinu.studyhubserver.support.fixture;

import kr.co.studyhubinu.studyhubserver.bookmark.domain.BookmarkEntity;

public enum BookmarkEntityFixture {

    BOOKMARK_POST1(),
    BOOKMARK_POST2();

    BookmarkEntityFixture() {
    }

    public BookmarkEntity bookMarkEntity_생성(Long postId, Long userId) {
        return BookmarkEntity.builder()
                .postId(postId)
                .userId(userId)
                .build();
    }
}
