package kr.co.studyhubinu.studyhubserver.studypost.service;

import kr.co.studyhubinu.studyhubserver.exception.study.PostNotFoundException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotAccessRightException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.studypost.domain.StudyPostEntity;
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
@Transactional(readOnly = true)
public class StudyPostService {

    private final StudyPostRepository studyPostRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long createPost(StudyPostInfo info) {
        UserEntity user = findUser(info.getUserId());
        StudyPostEntity studyPost = info.toEntity(user.getId());
        //studyPostValidator.validStudyPostDate(info.getStudyStartDate(), info.getStudyEndDate());
        return studyPostRepository.save(studyPost).getId();
    }

    @Transactional
    public Long updatePost(UpdateStudyPostInfo info) {
        UserEntity user = findUser(info.getUserId());
        StudyPostEntity post = findPost(info.getPostId());
        //studyPostValidator.validStudyPostDate(info.getStudyStartDate(), info.getStudyEndDate());
        validatePostByUser(user.getId(), post);
        post.update(info);
        return post.getId();
    }

    @Transactional
    public void deletePost(Long postId, Long userId) {
        UserEntity user = findUser(userId);
        StudyPostEntity post = findPost(postId);
        validatePostByUser(user.getId(), post);
        studyPostRepository.delete(post);
    }

    @Transactional
    public void closePost(Long postId, Long userId) {
        final UserEntity user = findUser(userId);
        final StudyPostEntity post = findPost(postId);
        validatePostByUser(user.getId(), post);
        post.close();
    }

    private UserEntity findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    private StudyPostEntity findPost(Long postId) {
        return studyPostRepository.findById(postId).orElseThrow(PostNotFoundException::new);
    }

    private void validatePostByUser(Long userId, StudyPostEntity post) {
        if (!post.isPostOfUser(userId)) throw new UserNotAccessRightException();
    }
}