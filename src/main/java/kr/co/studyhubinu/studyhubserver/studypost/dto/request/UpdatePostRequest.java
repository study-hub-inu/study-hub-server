package kr.co.studyhubinu.studyhubserver.studypost.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.UpdateStudyPostInfo;
import kr.co.studyhubinu.studyhubserver.study.enums.StudyWayType;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
public class UpdatePostRequest {

    @Schema(description = "수정할 투표 게시글 id", example = "1")
    @NotNull
    private Long postId;

    @Schema(description = "수정할 제목", example = "수정된 정보처리기사")
    @NotBlank
    private String title;

    @Schema(description = "수정할 내용", example = "화이팅")
    @NotBlank
    private String content;

    @Schema(description = "수정할 채팅방 url", example = "www.dfasdf")
    @NotBlank
    private String chatUrl;

    @Schema(description = "수정할 관련 학과", example = "COMPUTER_SCIENCE_ENGINEERING")
    @NotNull
    private MajorType major;

    @Schema(description = "수정할 스터디 정원", example = "16")
    @NotNull
    private int studyPerson;

    @Schema(description = "수정할 벌금", example = "10000 (벌금이 없다면 0 보내주세요!)")
    @NotNull
    private int penalty;

    @Schema(description = "수정할 벌금 방식 없으면 null 보내주시면 됩니다", example = "지각비")
    private String penaltyWay;

    @Schema(description = "수정할 필터 성별", example = "FEMALE")
    @NotNull
    private GenderType gender;

    @Schema(description = "수정할 스터디 방식", example = "CONTACT")
    @NotNull
    private StudyWayType studyWay;

    @Schema(description = "수정할 스터디 시작 날짜(ISO 8601)", example = "2023-08-23")
    @NotNull
    private LocalDate studyStartDate;

    @Schema(description = "수정할 스터디 종료 날짜(ISO 8601)", example = "2023-12-25")
    @NotNull
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
