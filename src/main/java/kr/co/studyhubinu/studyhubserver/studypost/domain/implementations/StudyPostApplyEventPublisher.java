package kr.co.studyhubinu.studyhubserver.studypost.domain.implementations;

import kr.co.studyhubinu.studyhubserver.exception.study.PostNotFoundException;
import kr.co.studyhubinu.studyhubserver.studypost.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.studypost.repository.StudyPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class StudyPostApplyEventPublisher {

    private final StudyPostRepository studyPostRepository;

    @Transactional
    public void acceptApplyEventPublish(Long studyId) {
        StudyPostEntity studyPost = studyPostRepository.findByIdWithPessimisticLock(studyId).orElseThrow(PostNotFoundException::new);
        studyPost.decreaseRemainingSeat();
        studyPostRepository.save(studyPost);
    }
}
