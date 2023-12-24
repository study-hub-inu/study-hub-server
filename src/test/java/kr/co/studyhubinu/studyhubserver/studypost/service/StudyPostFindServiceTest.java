package kr.co.studyhubinu.studyhubserver.studypost.service;

import kr.co.studyhubinu.studyhubserver.bookmark.repository.BookmarkRepository;
import kr.co.studyhubinu.studyhubserver.studypost.dto.request.InquiryRequest;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseByInquiry;
import kr.co.studyhubinu.studyhubserver.studypost.repository.StudyPostRepository;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserId;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class StudyPostFindServiceTest {

    @InjectMocks
    StudyPostFindService studyPostFindService;

    @Mock
    StudyPostRepository studyPostRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    BookmarkRepository bookmarkRepository;

//    @Test
//    void 스터디_전체_조회() {
//        // given
//        FindPostResponseByInquiry inquiry = FindPostResponseByInquiry.builder()
//                .postId(1L)
//                .build();
//        List<FindPostResponseByInquiry> inquiries = new ArrayList<>();
//        inquiries.add(inquiry);
//
//        Slice<FindPostResponseByInquiry> inquirieSlice = new SliceImpl<>(new ArrayList<>(), PageRequest.of(0, 3), false);
//        when(studyPostRepository.findByInquiry(any(), any(), anyLong())).thenReturn(inquiries);
//
//        // when
//        InquiryRequest inquiryRequest = InquiryRequest.builder().build();
//        Slice<FindPostResponseByInquiry> result = studyPostFindService.findAllPost(inquiryRequest, 1, 3, 1L);
//
//        // then
//        System.out.println(result.getContent());
//    }

    @Test
    void findPostById() {
    }

    @Test
    void getMyPosts() {
    }

    @Test
    void getBookmarkedPosts() {
    }
}