package kr.co.studyhubinu.studyhubserver.apply.dto.data;

import kr.co.studyhubinu.studyhubserver.apply.enums.Inspection;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Getter;

@Getter
public class ParticipateApplyData {

    private String major;
    private String title;
    private String content;
    private String chatUrl;
    private String inspection;
    private Long postId;

    public ParticipateApplyData(MajorType major, String title, String content, String chatUrl, Inspection inspection, Long postId) {
        this.major = major.getValue();
        this.title = title;
        this.content = content;
        this.chatUrl = chatUrl;
        this.inspection = inspection.getValue();
        this.postId = postId;
    }
}
