package kr.co.studyhubinu.studyhubserver.studypost.service;

import kr.co.studyhubinu.studyhubserver.exception.study.PostNotFoundException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.studypost.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.studypost.domain.StudyPostValidator;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.StudyPostInfo;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.UpdateStudyPostInfo;
import kr.co.studyhubinu.studyhubserver.studypost.repository.StudyPostRepository;
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
    private final StudyPostValidator studyPostValidator;


    public Long createPost(StudyPostInfo info) {
        UserEntity user = userRepository.findById(info.getUserId()).orElseThrow(UserNotFoundException::new);
        StudyPostEntity studyPost = info.toEntity(user.getId());
        studyPostValidator.validStudyPostDate(info.getStudyStartDate(), info.getStudyEndDate());
        return studyPostRepository.save(studyPost).getId();

    }

    public Long updatePost(UpdateStudyPostInfo info) {
        UserEntity user = userRepository.findById(info.getUserId()).orElseThrow(UserNotFoundException::new);
        StudyPostEntity post = studyPostRepository.findById(info.getPostId()).orElseThrow(PostNotFoundException::new);
        studyPostValidator.validStudyPostDate(info.getStudyStartDate(), info.getStudyEndDate());
        studyPostValidator.validIsPostOfUser(user.getId(), post);
        post.update(info);
        return post.getId();
    }

    public void deletePost(Long postId, Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        StudyPostEntity post = studyPostRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        studyPostValidator.validIsPostOfUser(user.getId(), post);
        studyPostRepository.delete(post);
    }
}