package kr.co.studyhubinu.studyhubserver.study.domain;

import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.userstudy.domain.UserStudyEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String content;

    @Column(name = "study_start_date")
    private LocalDate studyStartDate;

    @Column(name = "study_end_date")
    private LocalDate studyEndDate;

    @Column(name = "chat_room_url")
    private String chatRoomUrl;

    @Column(name = "user_id")
    private Long userId;

    @OneToMany(mappedBy = "study")
    private List<UserStudyEntity> userStudyEntityList;


    @Builder
    public StudyEntity(String name, String content, LocalDate studyStartDate, LocalDate studyEndDate, String chatRoomUrl) {
        this.name = name;
        this.content = content;
        this.studyStartDate = studyStartDate;
        this.studyEndDate = studyEndDate;
        this.chatRoomUrl = chatRoomUrl;
    }

}
