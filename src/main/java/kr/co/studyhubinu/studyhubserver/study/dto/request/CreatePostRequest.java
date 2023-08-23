package kr.co.studyhubinu.studyhubserver.study.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.studyhubinu.studyhubserver.study.dto.data.StudyPostInfo;
import kr.co.studyhubinu.studyhubserver.study.enums.StudyWayType;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

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

    @Schema(description = "스터디 정원", example = "10")
    @NotBlank
    private int studyPerson;

    @Schema(description = "벌금", example = "10000")
    @NotBlank
    private int penalty;

    @Schema(description = "필터 성별", example = "FEMALE")
    @NotBlank
    private GenderType gender;

    @Schema(description = "스터디 방식", example = "CONTACT")
    @NotBlank
    private StudyWayType studyWay;

    @Schema(description = "스터디 시작 날짜(ISO 8601)", example = "2023-08-23")
    @NotBlank
    private LocalDate startStartDate;

    @Schema(description = "스터디 종료 날짜(ISO 8601)", example = "2023-12-25")
    @NotBlank
    private LocalDate studyEndDate;

    @Schema(description = "관심사", example = "[\"컴퓨터공학부\", \"백엔드\", \"면접\"]")
    @NotBlank
    private List<String> interests;


    public StudyPostInfo toService(Long userId) {

        return StudyPostInfo.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .chatUrl(chatUrl)
                .studyPerson(studyPerson)
                .penalty(penalty)
                .gender(gender)
                .studyWay(studyWay)
                .startStartDate(startStartDate)
                .studyEndDate(studyEndDate)
                .interests(interests)
                .build();
    }
}
