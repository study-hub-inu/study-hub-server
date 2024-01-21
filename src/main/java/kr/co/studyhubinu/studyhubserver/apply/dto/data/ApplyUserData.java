package kr.co.studyhubinu.studyhubserver.apply.dto.data;

import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ApplyUserData {

    private Long id;
    private String nickname;
    private MajorType major;
    private String imageUrl;
    private String introduce;
    private LocalDateTime createdDate;

    @Builder
    public ApplyUserData(Long id, String nickname, MajorType major, String imageUrl, String introduce, LocalDateTime createdDate) {
        this.id = id;
        this.nickname = nickname;
        this.major = major;
        this.imageUrl = imageUrl;
        this.introduce = introduce;
        this.createdDate = createdDate;
    }
}
