package kr.co.studyhubinu.studyhubserver.studypost.domain.implementations;

import kr.co.studyhubinu.studyhubserver.exception.study.PostNotFoundException;
import kr.co.studyhubinu.studyhubserver.studypost.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.studypost.repository.StudyPostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@RequiredArgsConstructor
@Slf4j
public class StudyPostWriter {

    private final StudyPostRepository studyPostRepository;
    private final StudyPostReader studyPostReader;

    @Transactional
    public void updateStudyPostApply(Long studyPostId) {
        StudyPostEntity studyPost = studyPostRepository.findById(studyPostId).orElseThrow(PostNotFoundException::new);
        studyPost.decreaseRemainingSeat();
        studyPost.closeStudyPostIfRemainingSeatIsZero();
        studyPostRepository.saveAndFlush(studyPost);
    }

}
