package kr.co.studyhubinu.studyhubserver.study.service;

import kr.co.studyhubinu.studyhubserver.exception.study.PostNotFoundException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotAccessRightException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.study.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.study.dto.data.StudyPostInfo;
import kr.co.studyhubinu.studyhubserver.study.dto.data.UpdateStudyPostInfo;
import kr.co.studyhubinu.studyhubserver.study.dto.response.*;
import kr.co.studyhubinu.studyhubserver.study.repository.StudyPostRepository;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
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
        StudyPostEntity studyPost = info.toEntity(user.getId());

        studyPostRepository.save(studyPost);
    }

    public void updatePost(UpdateStudyPostInfo info) {
        UserEntity user = userRepository.findById(info.getUserId()).orElseThrow(UserNotFoundException::new);
        StudyPostEntity post = studyPostRepository.findById(info.getPostId()).orElseThrow(PostNotFoundException::new);
        isPostOfUser(user.getId(), post);
        post.update(info);
    }

    public void deletePost(Long postId, Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        StudyPostEntity post = studyPostRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        isPostOfUser(user.getId(), post);
        studyPostRepository.delete(post);
    }

    public void isPostOfUser(Long userId, StudyPostEntity post) {
        if (!post.isVoteOfUser(userId)) throw new UserNotAccessRightException();
    }

    public Slice<FindPostResponseByAll> findPostResponseByAll(Pageable pageable) {
        return studyPostRepository.findByAll(pageable);
    }

    public Slice<FindPostResponseByString> findPostResponseByString(String title, MajorType major, String content, Pageable pageable) {
        return studyPostRepository.findByString(title, major, content, pageable);
    }

    public Slice<GetMyPostResponse> getMyPosts(int page, int size, Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        return studyPostRepository.findByPostedUserId(user.getId(), pageable);
    }

    public Slice<GetBookmarkedPostsResponse> getBookmarkedPosts(int page, int size, Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        return studyPostRepository.findPostsByBookmarked(userId, pageable);
    }
}
