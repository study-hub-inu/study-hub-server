package kr.co.studyhubinu.studyhubserver.studypost.service;


import kr.co.studyhubinu.studyhubserver.apply.domain.ApplyEntity;
import kr.co.studyhubinu.studyhubserver.apply.repository.ApplyRepository;
import kr.co.studyhubinu.studyhubserver.bookmark.repository.BookmarkRepository;
import kr.co.studyhubinu.studyhubserver.common.dto.Converter;
import kr.co.studyhubinu.studyhubserver.exception.study.PostNotFoundException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.*;
import kr.co.studyhubinu.studyhubserver.studypost.dto.request.InquiryRequest;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseByBookmark;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseById;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseByInquiry;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseByUserId;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.*;
import kr.co.studyhubinu.studyhubserver.studypost.repository.StudyPostRepository;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class StudyPostFindService {

    private static final int POST_RECOMMEND_COUNT = 10;

    private final StudyPostRepository studyPostRepository;
    private final UserRepository userRepository;
    private final BookmarkRepository bookMarkRepository;
    private final ApplyRepository applyRepository;

    public FindPostResponseByInquiry findPostResponseByInquiry(final InquiryRequest inquiryRequest, final int page, final int size, Long userId) {
        final Pageable pageable = PageRequest.of(page, size);
        final Slice<PostDataByInquiry> posts = Converter.toSlice(pageable, studyPostRepository.findByInquiry(inquiryRequest, pageable, userId));
        return new FindPostResponseByInquiry((long) posts.getContent().size(), posts);
    }

    public FindPostResponseByBookmark getBookmarkedPosts(int page, int size, Long userId) {
        validateUser(userId);
        final Pageable pageable = PageRequest.of(page, size);
        final Long totalCount = getBookmarkCountByUserId(userId);
        final Slice<PostDataByBookmark> posts = Converter.toSlice(pageable, studyPostRepository.findPostsByBookmarked(userId, pageable));
        return new FindPostResponseByBookmark(totalCount, posts);
    }

    private Long getBookmarkCountByUserId(Long userId) {
        return bookMarkRepository.countByUserId(userId);
    }

    public FindPostResponseByUserId getMyPosts(int page, int size, Long userId) {
        final UserEntity user = findUser(userId);
        final Pageable pageable = PageRequest.of(page, size);
        final Long totalCount = getPostCountByUserId(userId);
        final Slice<PostDataByUserId> posts = Converter.toSlice(pageable, studyPostRepository.findByPostedUserId(user.getId(), pageable));
        return new FindPostResponseByUserId(totalCount, posts);
    }

    public FindPostResponseById findPostById(Long postId, Long userId) {
        final PostData postData = findPostDataById(postId, userId);
        boolean isApply = getUserApply(userId, postData);

        return new FindPostResponseById(postData, getRelatedPosts(postData.getMajor(), postId), isApply);
    }

    private boolean getUserApply(Long userId, PostData postData) {
        Optional<ApplyEntity> apply = applyRepository.findByUserIdAndStudyId(userId, postData.getStudyId());
        return apply.isPresent();
    }

    public List<PostDataByMajor> getRelatedPosts(MajorType major, Long exceptPostId) {
        return studyPostRepository.findByMajor(major, exceptPostId);
    }

    private PostData findPostDataById(Long postId, Long userId) {
        return studyPostRepository.findPostById(postId, userId).orElseThrow(PostNotFoundException::new);
    }

    private Long getPostCountByUserId(Long userId) {
        return studyPostRepository.countByPostedUserId(userId);
    }

    private UserEntity findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    private void validateUser(Long userId) {
        userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public FindRecommendPostsResponse findRecommendPosts(String keyword) {
        List<String> recommendPosts = studyPostRepository.findPostsByTitleStartWith(keyword, POST_RECOMMEND_COUNT);
        return new FindRecommendPostsResponse(recommendPosts);
    }
}
