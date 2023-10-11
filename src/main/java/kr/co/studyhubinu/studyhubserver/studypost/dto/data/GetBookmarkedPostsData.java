package kr.co.studyhubinu.studyhubserver.studypost.dto.data;

import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetBookmarkedPostsData {
    private Long postId;
    private MajorType major;
    private String title;
    private String content;
    private int remainingSeat;
    private boolean close;
}
