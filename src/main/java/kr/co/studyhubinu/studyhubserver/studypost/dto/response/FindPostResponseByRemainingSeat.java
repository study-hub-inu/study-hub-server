package kr.co.studyhubinu.studyhubserver.studypost.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindPostResponseByRemainingSeat {

    private Long postId;
    private String title;
    private int person;
    private int remainingSeat;

}
