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

    public ParticipateApplyData(MajorType major, String title, String content, String chatUrl, Inspection inspection) {
        this.major = major.getValue();
        this.title = title;
        this.content = content;
        this.chatUrl = chatUrl;
        this.inspection = inspection.getValue();
    }
}
