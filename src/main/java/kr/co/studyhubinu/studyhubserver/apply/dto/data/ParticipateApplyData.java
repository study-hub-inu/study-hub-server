package kr.co.studyhubinu.studyhubserver.apply.dto.data;

import kr.co.studyhubinu.studyhubserver.apply.enums.Inspection;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Getter;

@Getter
public class ParticipateApplyData {

    private final String major;
    private final String title;
    private final String content;
    private final String chatUrl;
    private final String inspection;
    private final Long postId;
    private final Long studyId;

    public ParticipateApplyData(MajorType major, String title, String content, String chatUrl, Inspection inspection, Long postId, Long studyId) {
        this.major = major.getValue();
        this.title = title;
        this.content = content;
        this.chatUrl = chatUrl;
        this.inspection = inspection.getValue();
        this.postId = postId;
        this.studyId = studyId;
    }
}
