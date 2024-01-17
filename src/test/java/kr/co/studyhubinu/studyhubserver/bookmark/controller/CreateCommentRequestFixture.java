package kr.co.studyhubinu.studyhubserver.bookmark.controller;

import kr.co.studyhubinu.studyhubserver.comment.dto.request.CreateCommentRequest;

public enum CreateCommentRequestFixture {

    CORRECT_CREATE_COMMENT(1L, "신입생도 참여할 수 있나요?");
    private final Long postId;
    private final String content;


    CreateCommentRequestFixture(Long postId, String content) {
        this.postId = postId;
        this.content = content;
    }

    public static CreateCommentRequest createCommentRequest_생성(CreateCommentRequestFixture fixture) {
        return CreateCommentRequest.builder()
                .postId(fixture.postId)
                .content(fixture.content)
                .build();
    }

}
