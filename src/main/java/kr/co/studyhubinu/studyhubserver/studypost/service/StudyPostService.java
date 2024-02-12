package kr.co.studyhubinu.studyhubserver.studypost.service;

import kr.co.studyhubinu.studyhubserver.exception.study.PostEndDateConflictException;
import kr.co.studyhubinu.studyhubserver.exception.study.PostNotFoundException;
import kr.co.studyhubinu.studyhubserver.exception.study.PostStartDateConflictException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotAccessRightException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.study.repository.StudyRepository;
import kr.co.studyhubinu.studyhubserver.study.domain.StudyEntity;
import kr.co.studyhubinu.studyhubserver.studypost.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.UpdateStudyPostInfo;
import kr.co.studyhubinu.studyhubserver.studypost.dto.request.CreatePostRequest;
import kr.co.studyhubinu.studyhubserver.studypost.repository.StudyPostRepository;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class StudyPostService {

    private final StudyPostRepository studyPostRepository;
    private final UserRepository userRepository;
    private final StudyRepository studyRepository;

    @Transactional
    public Long createPost(CreatePostRequest post, Long userId) {
        isExistUser(userId);
        validStudyPostDate(post.getStudyStartDate(), post.getStudyEndDate());
        Long studyId = createStudy(post, userId);
        StudyPostEntity studyPost = post.toStudyPostEntity(userId, studyId);
        return studyPostRepository.save(studyPost).getId();
    }

    @Transactional
    public Long createStudy(CreatePostRequest post, Long userId) {
        StudyEntity study = studyRepository.save(post.toStudyEntity(userId));
        return study.getId();
    }

    @Transactional
    public Long updatePost(UpdateStudyPostInfo info) {
        UserEntity user = findUser(info.getUserId());
        StudyPostEntity post = findPost(info.getPostId());
        validStudyPostDate(info.getStudyStartDate(), info.getStudyEndDate());
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

    private void isExistUser(Long userId) {
        userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
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

    private void validStudyPostDate(LocalDate studyStartDate, LocalDate studyEndDate) {
        LocalDate now = LocalDate.now();

        validateStartDateOverEndDate(studyStartDate, studyEndDate);
        validateStartDateBeforeNow(studyStartDate, now);
    }

    private void validateStartDateBeforeNow(LocalDate studyStartDate, LocalDate now) {
        if (now.isAfter(studyStartDate)) {
            throw new PostStartDateConflictException();
        }
    }

    private void validateStartDateOverEndDate(LocalDate studyStartDate, LocalDate studyEndDate) {
        if (studyStartDate.isAfter(studyEndDate)) {
            throw new PostEndDateConflictException();
        }
    }

    @Transactional
    public void deleteAllPosts(Long userId) {
        isExistUser(userId);
        studyPostRepository.deleteAllStudyPostByUserId(userId);
    }
}