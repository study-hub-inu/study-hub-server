package kr.co.studyhubinu.studyhubserver.support.fixture;

import kr.co.studyhubinu.studyhubserver.comment.domain.CommentEntity;

public enum CommentEntityFixture {

    COMMENT_1("신입생도 참가할 수 있나요?"),
    COMMENT_2("4학년 막학기도 참여할 수 있나요?");

    private final String content;

    CommentEntityFixture(String content) {
        this.content = content;
    }

    public CommentEntity commentEntity_생성(Long userId, Long postId) {
        return CommentEntity.builder()
                .postId(postId)
                .userId(userId)
                .content(this.content)
                .build();
    }
}
