package kr.co.studyhubinu.studyhubserver.studypost.repository;

import kr.co.studyhubinu.studyhubserver.studypost.dto.data.GetBookmarkedPostsData;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.RelatedPostData;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseByAll;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.PostData;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseByRemainingSeat;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseByString;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;


public interface StudyPostRepositoryCustom {

    Slice<FindPostResponseByString> findByString(String title, MajorType majorType, String content, Pageable pageable);

    Slice<FindPostResponseByAll> findByAll(Pageable pageable);

    Slice<GetBookmarkedPostsData> findPostsByBookmarked(Long userId, Pageable pageable);

    Slice<FindPostResponseByRemainingSeat> findPostsByRemainingSeat(Pageable pageable);

    Optional<PostData> findPostByIdAndUserId(Long postId, Long userId);

    List<RelatedPostData> findByMajor(MajorType major, Long exceptPostId);

    Optional<PostData> findPostById(Long postId);

    //Slice<StudyPostEntity> findByBookMark(Pageable pageable);
}

