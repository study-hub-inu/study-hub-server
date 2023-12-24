package kr.co.studyhubinu.studyhubserver.studypost.repository;

import kr.co.studyhubinu.studyhubserver.studypost.dto.data.*;
import kr.co.studyhubinu.studyhubserver.studypost.dto.request.InquiryRequest;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface StudyPostRepositoryCustom {

    List<PostDataByInquiry> findByInquiry(final InquiryRequest inquiryRequest, Pageable pageable, Long userId);

    List<PostDataByBookmark> findPostsByBookmarked(Long userId, Pageable pageable);

    List<PostDataByMajor> findByMajor(MajorType major, Long exceptPostId);

    List<PostDataByUserId> findByPostedUserId(Long userId, Pageable pageable);

    Optional<PostData> findPostById(Long postId, Long userId);

    List<String> findPostsByTitleStartWith(String keyword, int postRecommendCount);
}

