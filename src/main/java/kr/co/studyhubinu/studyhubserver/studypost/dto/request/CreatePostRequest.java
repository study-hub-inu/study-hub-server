package kr.co.studyhubinu.studyhubserver.studypost.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.studyhubinu.studyhubserver.study.domain.StudyEntity;
import kr.co.studyhubinu.studyhubserver.studypost.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.study.enums.StudyWayType;
import kr.co.studyhubinu.studyhubserver.studypost.validate.MinStudyPerson;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CreatePostRequest {

    @Schema(description = "제목", example = "정보처리기사")
    @NotBlank(message = "제목 작성은 필수입니다!")
    private String title;

    @Schema(description = "내용", example = "화이팅")
    @NotBlank(message = "내용 작성은 필수입니다!")
    private String content;

    @Schema(description = "채팅방 url", example = "www.dfasdf")
    @NotBlank(message = "채팅방 url 작성은 필수입니다!")
    private String chatUrl;

    @Schema(description = "관련 학과", example = "COMPUTER_SCIENCE_ENGINEERING")
    @NotNull(message = "관련 학과 작성은 필수입니다!")
    private MajorType major;

    @Schema(description = "스터디 정원", example = "10")
    @MinStudyPerson
    private Integer studyPerson;

    @Schema(description = "벌금", example = "10000 (벌금이 없다면 0 보내주세요!)")
    @NotNull
    private Integer penalty;

    @Schema(description = "벌금 방식 없으면 null 보내주시면 됩니다", example = "지각비")
    private String penaltyWay;

    @Schema(description = "마감 여부", example = "false (default 값이 false 로 설정되어 있습니다, 따로 기입 안해주셔도 된다는 뜻)")
    private boolean close;

    @Schema(description = "필터 성별", example = "FEMALE")
    @NotNull(message = "허용 성별 작성은 필수입니다!")
    private GenderType gender;

    @Schema(description = "스터디 방식", example = "CONTACT")
    @NotNull(message = "스터디 방식 작성은 필수입니다!")
    private StudyWayType studyWay;

    @Schema(description = "스터디 시작 날짜(ISO 8601)", example = "2024-06-23")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull(message = "스터디 시작 날짜 작성은 필수입니다!")
    private LocalDate studyStartDate;

    @Schema(description = "스터디 종료 날짜(ISO 8601)", example = "2024-12-25")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull(message = "스터디 종료 날짜 작성은 필수입니다!")
    private LocalDate studyEndDate;

    @Builder
    public CreatePostRequest(String title, String content, String chatUrl, MajorType major, int studyPerson, int penalty, String penaltyWay, boolean close, GenderType gender, StudyWayType studyWay, LocalDate studyStartDate, LocalDate studyEndDate) {
        this.title = title;
        this.content = content;
        this.chatUrl = chatUrl;
        this.major = major;
        this.studyPerson = studyPerson;
        this.penalty = penalty;
        this.penaltyWay = penaltyWay;
        this.close = close;
        this.gender = gender;
        this.studyWay = studyWay;
        this.studyStartDate = studyStartDate;
        this.studyEndDate = studyEndDate;
    }

    public StudyEntity toStudyEntity(Long userId) {
        return StudyEntity.builder()
                .title(title)
                .content(content)
                .chatUrl(chatUrl)
                .studyStartDate(studyStartDate)
                .studyEndDate(studyEndDate)
                .userId(userId)
                .major(major)
                .build();
    }

    public StudyPostEntity toStudyPostEntity(Long userId, Long studyId) {
        return StudyPostEntity.builder()
                .title(title)
                .content(content)
                .chatUrl(chatUrl)
                .major(major)
                .studyPerson(studyPerson)
                .filteredGender(gender)
                .studyWay(studyWay)
                .penalty(penalty)
                .penaltyWay(penaltyWay)
                .studyStartDate(studyStartDate)
                .studyEndDate(studyEndDate)
                .postedUserId(userId)
                .remainingSeat(studyPerson)
                .studyId(studyId)
                .build();
    }
}
