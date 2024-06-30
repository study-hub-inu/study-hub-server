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
@Transactional(readOnly = true)
@Slf4j
public class StudyPostReader {

    private final StudyPostRepository studyPostRepository;

    public StudyPostEntity readByStudyId(Long studyId) {
        log.info("dddddddddd 게시글 읽음");
        return studyPostRepository.findByStudyId(studyId).orElseThrow(PostNotFoundException::new);
    }

}
