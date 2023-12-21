package kr.co.studyhubinu.studyhubserver.studypost.service;

import kr.co.studyhubinu.studyhubserver.bookmark.repository.BookmarkRepository;
import kr.co.studyhubinu.studyhubserver.common.dto.Converter;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.PostDataByBookmark;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.PostDataByInquiry;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.PostDataByUserId;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseByBookmark;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseByInquiry;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseByUserId;
import kr.co.studyhubinu.studyhubserver.studypost.repository.StudyPostRepository;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
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
                () -> assertThat(postResponse.getTotalCount()).isEqualTo(1L),
                () -> assertThat(postResponse.getPostDataByInquiries().getContent().get(0).getTitle()).isEqualTo("Hello World")
        );
    }

    @Test
    void 북마크한_게시글_조회() {
        // given
        Pageable pageable = PageRequest.of(0,1);
        PostDataByBookmark post = PostDataByBookmark.builder()
                .postId(1L)
                .title("안녕하세요 세상이여")
                .build();

        List<PostDataByBookmark> posts = new ArrayList<>();
        posts.add(post);

        // when
        FindPostResponseByBookmark postResponse = new FindPostResponseByBookmark(1L, Converter.toSlice(pageable, posts));

        // then
        assertAll(
                () -> assertThat(postResponse.getTotalCount()).isEqualTo(1L),
                () -> assertThat(postResponse.getGetBookmarkedPostsData().getContent().get(0).getTitle()).isEqualTo("안녕하세요 세상이여")
        );
    }

    @Test
    void 내가_쓴_게시글_조회() {
        // given
        Pageable pageable = PageRequest.of(0,1);
        PostDataByUserId post = PostDataByUserId.builder()
                .postId(1L)
                .title("덤벼라 세상아")
                .build();

        List<PostDataByUserId> posts = new ArrayList<>();
        posts.add(post);

        when(studyPostRepository.findByPostedUserId(1L, pageable)).thenReturn(posts);

        // when
        FindPostResponseByUserId postResponse = new FindPostResponseByUserId(1L, Converter.toSlice(pageable, studyPostRepository.findByPostedUserId(1L, pageable)));

        // then
        assertAll(
                () -> assertThat(postResponse.getTotalCount().equals(1L)),
                () -> assertThat(postResponse.getPosts().getContent().get(0).getTitle().equals("덤벼라 세상아"))
        );
    }

    @Test
    void getBookmarkedPosts() {
    }
}