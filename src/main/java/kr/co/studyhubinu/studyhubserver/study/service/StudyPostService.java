package kr.co.studyhubinu.studyhubserver.study.service;

import kr.co.studyhubinu.studyhubserver.bookmark.repository.BookMarkRepository;
import kr.co.studyhubinu.studyhubserver.exception.study.PostNotFoundException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.study.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.study.domain.StudyPostValidator;
import kr.co.studyhubinu.studyhubserver.study.dto.data.GetBookmarkedPostsData;
import kr.co.studyhubinu.studyhubserver.study.dto.data.GetMyPostData;
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
    private final StudyPostValidator studyPostValidator;
    private final BookMarkRepository bookMarkRepository;


    public void createPost(StudyPostInfo info) {
        UserEntity user = userRepository.findById(info.getUserId()).orElseThrow(UserNotFoundException::new);
        StudyPostEntity studyPost = info.toEntity(user.getId());

        studyPostValidator.validStudyPostDate(info.getStudyStartDate(), info.getStudyEndDate());
        studyPostRepository.save(studyPost);
    }

    public void updatePost(UpdateStudyPostInfo info) {
        UserEntity user = userRepository.findById(info.getUserId()).orElseThrow(UserNotFoundException::new);
        StudyPostEntity post = studyPostRepository.findById(info.getPostId()).orElseThrow(PostNotFoundException::new);
        studyPostValidator.validStudyPostDate(info.getStudyStartDate(), info.getStudyEndDate());
        studyPostValidator.validIsPostOfUser(user.getId(), post);
        post.update(info);
    }

    public void deletePost(Long postId, Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        StudyPostEntity post = studyPostRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        studyPostValidator.validIsPostOfUser(user.getId(), post);
        studyPostRepository.delete(post);
    }

    public Slice<FindPostResponseByAll> findPostResponseByAll(Pageable pageable) {
        return studyPostRepository.findByAll(pageable);
    }

    public Slice<FindPostResponseByString> findPostResponseByString(String title, MajorType major, String content, Pageable pageable) {
        return studyPostRepository.findByString(title, major, content, pageable);
    }

    public GetMyPostResponse getMyPosts(int page, int size, Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        Long totalCount = studyPostRepository.countByPostedUserId(userId);
        Slice<GetMyPostData> getMyPostData = studyPostRepository.findByPostedUserId(user.getId(), pageable);
        return new GetMyPostResponse(totalCount, getMyPostData);
    }

    public GetBookmarkedPostsResponse getBookmarkedPosts(int page, int size, Long userId) {
        userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        Long totalCount = bookMarkRepository.countByUserId(userId);
        Slice<GetBookmarkedPostsData> getBookmarkedPostsData = studyPostRepository.findPostsByBookmarked(userId, pageable);
        return new GetBookmarkedPostsResponse(totalCount, getBookmarkedPostsData);
    }

//    public Slice<StudyPostEntity> findPostResponseByBookMark(Pageable pageable) {
//        return studyPostRepository.findByBookMark(pageable);
//    }
}
