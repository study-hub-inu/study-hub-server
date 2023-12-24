package kr.co.studyhubinu.studyhubserver.studypost.service;


import kr.co.studyhubinu.studyhubserver.bookmark.repository.BookmarkRepository;
import kr.co.studyhubinu.studyhubserver.exception.study.PostNotFoundException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.GetBookmarkedPostsData;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.GetMyPostData;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.PostData;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.RelatedPostData;
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

    public Slice<FindPostResponseByInquiry> findPostResponseByInquiry(final InquiryRequest inquiryRequest, final int page, final int size, Long userId) {
        return studyPostRepository.findByInquiry(inquiryRequest, PageRequest.of(page, size), userId);
    }

    public FindPostResponseById findPostById(Long postId, Long userId) {
        PostData postData = studyPostRepository.findPostById(postId, userId).orElseThrow(PostNotFoundException::new);
        return new FindPostResponseById(postData, getRelatedPosts(postData.getMajor(), postId));
    }

    public GetMyPostResponse getMyPosts(int page, int size, Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Pageable pageable = PageRequest.of(page, size);
        Long totalCount = studyPostRepository.countByPostedUserId(userId);
        Slice<GetMyPostData> getMyPostData = studyPostRepository.findSliceByPostedUserId(user.getId(), pageable);
        return new GetMyPostResponse(totalCount, getMyPostData);
    }

    public GetBookmarkedPostsResponse getBookmarkedPosts(int page, int size, Long userId) {
        isExistUser(userId);
        Pageable pageable = PageRequest.of(page, size);
        Long totalCount = bookMarkRepository.countByUserId(userId);
        Slice<GetBookmarkedPostsData> getBookmarkedPostsData = studyPostRepository.findPostsByBookmarked(userId, pageable);
        return new GetBookmarkedPostsResponse(totalCount, getBookmarkedPostsData);
    }

    private void isExistUser(Long userId) {
        userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    private List<RelatedPostData> getRelatedPosts(MajorType major, Long exceptPostId) {
        return studyPostRepository.findByMajor(major, exceptPostId);
    }

    public FindRecommendPostsResponse findRecommendPosts(String keyword) {
        List<String> recommendPosts = studyPostRepository.findPostsByTitle(keyword, POST_RECOMMEND_COUNT);
        return new FindRecommendPostsResponse(recommendPosts);
    }
}
