package kr.co.studyhubinu.studyhubserver.user.domain;

import kr.co.studyhubinu.studyhubserver.bookmark.repository.BookMarkRepository;
import kr.co.studyhubinu.studyhubserver.study.StudyRepository;
import kr.co.studyhubinu.studyhubserver.studypost.repository.StudyPostRepository;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserActivityCountData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserActivityFinder {

    private final StudyPostRepository studyPostRepository;
    private final BookMarkRepository bookMarkRepository;
    private final StudyRepository studyRepository;

    public UserActivityCountData countUserActivity(Long userId) {
        Long postCount = studyPostRepository.countByPostedUserId(userId);
        Long participateCount = 0L;
        Long bookmarkCount = bookMarkRepository.countByUserId(userId);
        return new UserActivityCountData(postCount, participateCount, bookmarkCount);
    }

}
