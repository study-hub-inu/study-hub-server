package kr.co.studyhubinu.studyhubserver.apply.dto.request;

import lombok.Getter;

@Getter
public class RefuseUserStudyRequestDto {

    private Long userId;

    private Long studyId;
}
