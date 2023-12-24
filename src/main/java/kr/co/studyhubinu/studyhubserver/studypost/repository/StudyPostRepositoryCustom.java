package kr.co.studyhubinu.studyhubserver.studypost.repository;

import kr.co.studyhubinu.studyhubserver.studypost.dto.data.GetBookmarkedPostsData;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.PostData;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.RelatedPostData;
import kr.co.studyhubinu.studyhubserver.studypost.dto.request.InquiryRequest;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseByInquiry;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;


public interface StudyPostRepositoryCustom {

    Slice<FindPostResponseByInquiry> findByInquiry(final InquiryRequest inquiryRequest, Pageable pageable, Long userId);

    Slice<GetBookmarkedPostsData> findPostsByBookmarked(Long userId, Pageable pageable);

    List<RelatedPostData> findByMajor(MajorType major, Long exceptPostId);

    Optional<PostData> findPostById(Long postId, Long userId);

    List<String> findPostsByTitle(String keyword, int postRecommendCount);
}

