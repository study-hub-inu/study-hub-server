package kr.co.studyhubinu.studyhubserver.apply.dto.data;

import kr.co.studyhubinu.studyhubserver.apply.enums.Inspection;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RejectApplyUserData {

    private final Long id;
    private final String nickname;
    private final MajorType major;
    private final String imageUrl;
    private final String introduce;
    private final LocalDateTime createdDate;
    private final Inspection inspection;
    private final String rejectReason;

    @Builder
    public RejectApplyUserData(Long id, String nickname, MajorType major, String imageUrl, String introduce, LocalDateTime createdDate, Inspection inspection, String rejectReason) {
        this.id = id;
        this.nickname = nickname;
        this.major = major;
        this.imageUrl = imageUrl;
        this.introduce = introduce;
        this.createdDate = createdDate;
        this.inspection = inspection;
        this.rejectReason = rejectReason;
    }
}
