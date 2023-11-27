package kr.co.studyhubinu.studyhubserver.user.dto.request;

import kr.co.studyhubinu.studyhubserver.user.dto.data.UpdateMajorInfo;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateMajorRequest {

    private MajorType major;

    @Builder
    public UpdateMajorRequest(MajorType major) {
        this.major = major;
    }

    public UpdateMajorInfo toService(Long userId) {
        return UpdateMajorInfo.builder()
                .userId(userId)
                .major(major)
                .build();
    }
}
