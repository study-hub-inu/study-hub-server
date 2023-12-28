package kr.co.studyhubinu.studyhubserver.bookmark.controller;

import kr.co.studyhubinu.studyhubserver.comment.dto.request.UpdateCommentRequest;

public enum UpdateCommentRequestFixture {
    CORRECT_COMMENT(1L, "4학년도 참여할 수 있나요?");

    private final Long commentId;
    private final String content;


    UpdateCommentRequestFixture(Long commentId, String content) {
        this.commentId = commentId;
        this.content = content;
    }

    public static UpdateCommentRequest updateCommentRequest_생성(UpdateCommentRequestFixture fixture) {
        return UpdateCommentRequest.builder()
                .commentId(fixture.commentId)
                .content(fixture.content)
                .build();
    }
}
