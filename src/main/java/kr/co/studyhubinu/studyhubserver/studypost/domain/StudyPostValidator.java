package kr.co.studyhubinu.studyhubserver.studypost.domain;

import kr.co.studyhubinu.studyhubserver.exception.study.PostDateConflictException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotAccessRightException;
import kr.co.studyhubinu.studyhubserver.studypost.domain.StudyPostEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class StudyPostValidator {

    public void validIsPostOfUser(Long userId, StudyPostEntity post) {
        if (!post.isPostOfUser(userId)) throw new UserNotAccessRightException();
    }

    public void validStudyPostDate(LocalDate studyStartDate, LocalDate studyEndDate) {
        if (studyStartDate.isAfter(studyEndDate)) {
            throw new PostDateConflictException();
        }
    }
}
