package kr.co.studyhubinu.studyhubserver.user.domain;

import kr.co.studyhubinu.studyhubserver.apply.repository.ApplyRepository;
import kr.co.studyhubinu.studyhubserver.studypost.repository.StudyPostRepository;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserActivityCountData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static kr.co.studyhubinu.studyhubserver.apply.enums.Inspection.ACCEPT;

@Component
@RequiredArgsConstructor
public class UserActivityFinder {

    private final StudyPostRepository studyPostRepository;
    private final ApplyRepository applyRepository;

    public UserActivityCountData countUserActivity(Long userId) {
        Long postCount = studyPostRepository.countByPostedUserId(userId);
        Long participateCount = applyRepository.countByUserIdAndInspection(userId, ACCEPT);
        Long applyCount = applyRepository.countByUserId(userId);
        return new UserActivityCountData(postCount, participateCount, applyCount);
    }

}
