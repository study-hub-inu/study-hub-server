package kr.co.studyhubinu.studyhubserver.study.dto.response;

import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.*;

@Getter
@Setter // QueryDsl 때문에 필요함
@NoArgsConstructor
@AllArgsConstructor
public class GetBookmarkedPostsResponse {

    private Long postId;
    private MajorType major;
    private String title;
    private String content;
    private int remainingSeat;
    private boolean close;

}
