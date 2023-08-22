package kr.co.studyhubinu.studyhubserver.study.dto.data;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class StudyPostInfo {

    private String information;
    private LocalDate studyStartDate;
    private LocalDate studyEndDate;
    private int penalty;

}
