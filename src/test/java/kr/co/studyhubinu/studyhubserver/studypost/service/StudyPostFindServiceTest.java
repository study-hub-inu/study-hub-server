package kr.co.studyhubinu.studyhubserver.studypost.service;

import kr.co.studyhubinu.studyhubserver.bookmark.repository.BookmarkRepository;
import kr.co.studyhubinu.studyhubserver.common.dto.Converter;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.PostDataByBookmark;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.PostDataByInquiry;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseByBookmark;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseByInquiry;
import kr.co.studyhubinu.studyhubserver.studypost.repository.StudyPostRepository;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
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

    @Test
    void 스터디_게시글_전체_조회() {
        // given
        PostDataByInquiry inquiry = PostDataByInquiry.builder()
                .postId(1L)
                .title("Hello World")
                .build();
        Pageable pageable = PageRequest.of(0,1);
        List<PostDataByInquiry> posts = new ArrayList<>();
        posts.add(inquiry);

        when(studyPostRepository.findByInquiry(any(), any(), anyLong())).thenReturn(posts);

        // when
        FindPostResponseByInquiry postResponse = new FindPostResponseByInquiry((long) pageable.getPageSize(),
                Converter.toSlice(pageable, studyPostRepository.findByInquiry(any(), any(), anyLong())));

        // then
        assertAll(
                () -> assertThat(postResponse.getTotalCount().equals(1L)),
                () -> assertThat(postResponse.getPostDataByInquiries().getContent().get(0).getTitle().equals("Hello World"))
        );
    }

    @Test
    void 북마크한_게시글_조회() {

    }

    @Test
    void getMyPosts() {
    }

    @Test
    void getBookmarkedPosts() {
    }
}