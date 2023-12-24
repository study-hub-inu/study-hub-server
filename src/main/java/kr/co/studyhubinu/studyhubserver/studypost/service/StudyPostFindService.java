package kr.co.studyhubinu.studyhubserver.studypost.service;


import kr.co.studyhubinu.studyhubserver.bookmark.repository.BookmarkRepository;
import kr.co.studyhubinu.studyhubserver.common.dto.Converter;
import kr.co.studyhubinu.studyhubserver.exception.study.PostNotFoundException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.*;
import kr.co.studyhubinu.studyhubserver.studypost.dto.request.InquiryRequest;
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

@RequiredArgsConstructor
@Service
@Transactional
public class StudyPostFindService {

    private static final int POST_RECOMMEND_COUNT = 5;

    private final StudyPostRepository studyPostRepository;
    private final UserRepository userRepository;
    private final BookmarkRepository bookMarkRepository;

    public FindPostResponseByInquiry findPostResponseByInquiry(final InquiryRequest inquiryRequest, final int page, final int size, Long userId) {
        final Pageable pageable = PageRequest.of(page, size);
        final Slice<PostDataByInquiry> posts = Converter.toSlice(pageable, studyPostRepository.findByInquiry(inquiryRequest, pageable, userId));

        return new FindPostResponseByInquiry((long) size, posts);
    }

    public FindPostResponseByBookmark getBookmarkedPosts(int page, int size, Long userId) {
        isExistUser(userId);
        final Pageable pageable = PageRequest.of(page, size);
        final Long totalCount = bookMarkRepository.countByUserId(userId);

        final Slice<PostDataByBookmark> posts = Converter.toSlice(pageable, studyPostRepository.findPostsByBookmarked(userId, pageable));

        return new FindPostResponseByBookmark(totalCount, posts);
    }

    public FindPostResponseByUserId getMyPosts(int page, int size, Long userId) {
        final UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        final Pageable pageable = PageRequest.of(page, size);
        final Long totalCount = studyPostRepository.countByPostedUserId(userId);

        final Slice<PostDataByUserId> posts = Converter.toSlice(pageable, studyPostRepository.findByPostedUserId(user.getId(), pageable));

        return new FindPostResponseByUserId(totalCount, posts);
    }

    public FindPostResponseById findPostById(Long postId, Long userId) {
        final PostData postData = studyPostRepository.findPostById(postId, userId).orElseThrow(PostNotFoundException::new);

        return new FindPostResponseById(postData, getRelatedPosts(postData.getMajor(), postId));
    }

    private void isExistUser(Long userId) {
        userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    private List<PostDataByMajor> getRelatedPosts(MajorType major, Long exceptPostId) {
        return studyPostRepository.findByMajor(major, exceptPostId);
    }

    public FindRecommendPostsResponse findRecommendPosts(String keyword) {
        List<String> recommendPosts = studyPostRepository.findPostsByTitleStartWith(keyword, POST_RECOMMEND_COUNT);
        return new FindRecommendPostsResponse(recommendPosts);
    }
}
