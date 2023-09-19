package kr.co.studyhubinu.studyhubserver.user.dto.data;

import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateMajorInfo {
    private Long userId;
    private MajorType major;

    @Builder
    public UpdateMajorInfo(Long userId, MajorType major) {
        this.userId = userId;
        this.major = major;
    }
}
