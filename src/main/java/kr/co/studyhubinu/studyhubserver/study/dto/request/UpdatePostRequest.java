package kr.co.studyhubinu.studyhubserver.study.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.studyhubinu.studyhubserver.study.dto.data.UpdateStudyPostInfo;
import kr.co.studyhubinu.studyhubserver.study.enums.StudyWayType;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
public class UpdatePostRequest {

    @Schema(description = "수정할 투표 게시글 id", example = "1")
    private Long postId;

    @Schema(description = "수정할 제목", example = "정보처리기사")
    @NotBlank
    private String title;

    @Schema(description = "수정할 내용", example = "화이팅")
    @NotBlank
    private String content;

    @Schema(description = "수정할 채팅방 url", example = "www.dfasdf")
    @NotBlank
    private String chatUrl;

    @Schema(description = "수정할 관련 학과", example = "COMPUTER_SCIENCE")
    private MajorType major;

    @Schema(description = "수정할 스터디 정원", example = "10")
    @NotBlank
    private int studyPerson;

    @Schema(description = "수정할 벌금", example = "10000 (벌금이 없다면 null 보내주세요!)")
    @NotBlank
    private int penalty;

    @Schema(description = "수정할 벌금 방식", example = "지각비")
    private String penaltyWay;

    @Schema(description = "수정할 필터 성별", example = "FEMALE")
    @NotBlank
    private GenderType gender;

    @Schema(description = "수정할 스터디 방식", example = "CONTACT")
    @NotBlank
    private StudyWayType studyWay;

    @Schema(description = "수정할 스터디 시작 날짜(ISO 8601)", example = "2023-08-23")
    @NotBlank
    private LocalDate studyStartDate;

    @Schema(description = "수정할 스터디 종료 날짜(ISO 8601)", example = "2023-12-25")
    @NotBlank
    private LocalDate studyEndDate;

    public UpdateStudyPostInfo toService(Long userId) {
        return UpdateStudyPostInfo.builder()
                .postId(postId)
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
