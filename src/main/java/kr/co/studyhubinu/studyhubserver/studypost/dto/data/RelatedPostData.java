package kr.co.studyhubinu.studyhubserver.studypost.dto.data;

import kr.co.studyhubinu.studyhubserver.user.dto.data.UserData;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.Getter;

@Getter
public class RelatedPostData {
    private Long postId;
    private MajorType major;
    private int remainingSeat;
    private UserData postedUser;

    public RelatedPostData(Long postId, MajorType major, int remainingSeat, UserData postedUser) {
        this.postId = postId;
        this.major = major;
        this.remainingSeat = remainingSeat;
        this.postedUser = postedUser;
    }
}
