package kr.co.studyhubinu.studyhubserver.study.service;

import kr.co.studyhubinu.studyhubserver.study.dto.data.StudyPostInfo;
import kr.co.studyhubinu.studyhubserver.study.repository.StudyPostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class StudyPostService {

    private final StudyPostRepository studyPostRepository;

    public void registerPost(StudyPostInfo studyPostInfo) {

    }

    public void updatePost() {

    }

    public void deletePost() {

    }

}
