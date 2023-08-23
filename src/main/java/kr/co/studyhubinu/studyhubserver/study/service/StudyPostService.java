package kr.co.studyhubinu.studyhubserver.study.service;

import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.study.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.study.dto.data.StudyPostInfo;
import kr.co.studyhubinu.studyhubserver.study.repository.StudyPostRepository;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
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
    private final UserRepository userRepository;

    public void createPost(StudyPostInfo info) {

        UserEntity user = userRepository.findById(info.getUserId()).orElseThrow(UserNotFoundException::new);

        StudyPostEntity studyPost = info.toEntity(user);

        studyPostRepository.save(studyPost);

    }

    public void updatePost() {

    }

    public void deletePost() {

    }

}