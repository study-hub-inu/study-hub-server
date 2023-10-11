package kr.co.studyhubinu.studyhubserver.study.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.studyhubinu.studyhubserver.study.dto.data.StudyPostInfo;
import kr.co.studyhubinu.studyhubserver.study.enums.StudyWayType;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
public class CreatePostRequest {

    @Schema(description = "제목", example = "정보처리기사")
    @NotBlank
    private String title;

    @Schema(description = "내용", example = "화이팅")
    @NotBlank
    private String content;

    @Schema(description = "채팅방 url", example = "www.dfasdf")
    @NotBlank
    private String chatUrl;

    @Schema(description = "관련 학과", example = "COMPUTER_SCIENCE_ENGINEERING")
    private MajorType major;

    @Schema(description = "스터디 정원", example = "10")
    @NotNull
    private int studyPerson;

    @Schema(description = "벌금", example = "10000 (벌금이 없다면 0 보내주세요!)")
    @NotNull
    private int penalty;

    @Schema(description = "벌금 방식 없으면 null 보내주시면 됩니다", example = "지각비")
    private String penaltyWay;

    @Schema(description = "마감 여부", example = "false (default 값이 false 로 설정되어 있습니다, 따로 기입 안해주셔도 된다는 뜻)")
    private boolean close;

    @Schema(description = "필터 성별", example = "FEMALE")
    @NotNull
    private GenderType gender;

    @Schema(description = "스터디 방식", example = "CONTACT")
    @NotNull
    private StudyWayType studyWay;

    @Schema(description = "스터디 시작 날짜(ISO 8601)", example = "2023-08-23")
    @NotNull
    private LocalDate studyStartDate;

    @Schema(description = "스터디 종료 날짜(ISO 8601)", example = "2023-12-25")
    @NotNull
    private LocalDate studyEndDate;


    public StudyPostInfo toService(Long userId) {

        return StudyPostInfo.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .chatUrl(chatUrl)
                .major(major)
                .studyPerson(studyPerson)
                .penalty(penalty)
                .penaltyWay(penaltyWay)
                .gender(gender)
                .studyWay(studyWay)
                .studyStartDate(studyStartDate)
                .studyEndDate(studyEndDate)
                .build();
    }
}
