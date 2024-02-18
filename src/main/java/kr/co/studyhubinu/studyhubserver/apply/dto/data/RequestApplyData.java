package kr.co.studyhubinu.studyhubserver.apply.dto.data;

import kr.co.studyhubinu.studyhubserver.apply.enums.Inspection;
import lombok.Getter;

@Getter
public class RequestApplyData {

    private final Long studyId;
    private final String studyTitle;
    private final Inspection inspection;
    private final String introduce;
    private final Long postId;

    public RequestApplyData(Long studyId, String studyTitle, Inspection inspection, String introduce, Long postId) {
        this.studyId = studyId;
        this.studyTitle = studyTitle;
        this.inspection = inspection;
        this.introduce = introduce;
        this.postId = postId;
    }
}
