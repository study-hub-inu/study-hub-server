package kr.co.studyhubinu.studyhubserver.user.dto.request;

import kr.co.studyhubinu.studyhubserver.user.dto.data.UpdateMajorInfo;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Getter;

@Getter
public class UpdateMajorRequest {

    private MajorType major;

    public UpdateMajorInfo toService(Long userId) {
        return UpdateMajorInfo.builder()
                .userId(userId)
                .major(major)
                .build();
    }
}
