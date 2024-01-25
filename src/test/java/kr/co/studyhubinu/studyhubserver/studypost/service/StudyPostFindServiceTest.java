package kr.co.studyhubinu.studyhubserver.studypost.service;

import kr.co.studyhubinu.studyhubserver.apply.domain.ApplyEntity;
import kr.co.studyhubinu.studyhubserver.apply.repository.ApplyRepository;
import kr.co.studyhubinu.studyhubserver.bookmark.repository.BookmarkRepository;
import kr.co.studyhubinu.studyhubserver.exception.study.PostNotFoundException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.*;
import kr.co.studyhubinu.studyhubserver.studypost.dto.request.InquiryRequest;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseByBookmark;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseById;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseByInquiry;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseByUserId;
import kr.co.studyhubinu.studyhubserver.studypost.repository.StudyPostRepository;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
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

    @Mock
    ApplyRepository applyRepository;

    @Test
    void 스터디_게시글_전체_조회() {
        // given
        PostDataByInquiry inquiry = PostDataByInquiry.builder()
                .postId(1L)
                .title("Hello World")
                .build();
        Pageable pageable = PageRequest.of(0, 2);
        List<PostDataByInquiry> posts = new ArrayList<>();
        posts.add(inquiry);
        InquiryRequest inquiryRequest = InquiryRequest.builder().build();

        when(studyPostRepository.findByInquiry(any(), any(), anyLong())).thenReturn(posts);

        // when
        FindPostResponseByInquiry postResponse = studyPostFindService.findPostResponseByInquiry(inquiryRequest, pageable.getPageNumber(), pageable.getPageSize(), 1L);

        // then
        assertAll(
                () -> assertThat(postResponse.getTotalCount()).isEqualTo(1L),
                () -> assertThat(postResponse.getPostDataByInquiries().getContent().get(0).getTitle()).isEqualTo("Hello World")
        );
    }

    @Test
    void 북마크한_게시글_조회() {
        // given
        PostDataByBookmark post = PostDataByBookmark.builder()
                .postId(1L)
                .title("안녕하세요 세상이여")
                .build();

        List<PostDataByBookmark> posts = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 3);
        posts.add(post);

        Optional<UserEntity> userEntity = Optional.ofNullable(UserEntity.builder().build());
        when(userRepository.findById(anyLong())).thenReturn(userEntity);
        when(bookmarkRepository.countByUserId(anyLong())).thenReturn(1L);
        when(studyPostRepository.findPostsByBookmarked(anyLong(), any())).thenReturn(posts);

        // when
        FindPostResponseByBookmark postResponse = studyPostFindService.getBookmarkedPosts(pageable.getPageNumber(), pageable.getPageSize(), 1L);

        // then
        assertAll(
                () -> assertThat(postResponse.getTotalCount()).isEqualTo(1L),
                () -> assertThat(postResponse.getGetBookmarkedPostsData().getContent().get(0).getTitle()).isEqualTo("안녕하세요 세상이여")
        );
    }

    @Test
    void 북마크한_게시글_조회_실패_존재하지않는_유저() {
        // given
        Pageable pageable = PageRequest.of(0, 3);
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // when, then
        assertThrows(UserNotFoundException.class, () -> {
            studyPostFindService.getBookmarkedPosts(pageable.getPageNumber(), pageable.getPageSize(), 1L);
        });
    }

    @Test
    void 내가_쓴_게시글_조회() {
        // given
        when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(UserEntity.builder().id(1L).build()));
        when(studyPostRepository.countByPostedUserId(anyLong())).thenReturn(1L);
        Pageable pageable = PageRequest.of(0, 3);

        PostDataByUserId post = PostDataByUserId.builder()
                .postId(1L)
                .title("사랑찾아 인생찾아")
                .build();
        List<PostDataByUserId> posts = new ArrayList<>();
        posts.add(post);
        when(studyPostRepository.findByPostedUserId(anyLong(), any())).thenReturn(posts);

        // when
        FindPostResponseByUserId postResponse = studyPostFindService.getMyPosts(pageable.getPageNumber(), pageable.getPageSize(), 1L);

        // then
        assertAll(
                () -> assertThat(postResponse.getTotalCount()).isEqualTo(1),
                () -> assertThat(postResponse.getPosts().getContent().get(0).getTitle()).isEqualTo("사랑찾아 인생찾아")
        );
    }

    @Test
    void 내가_작성한_게시글_조회_실패_존재하지않는_유저() {
        // given
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        Pageable pageable = PageRequest.of(0, 3);

        // when, then
        assertThrows(UserNotFoundException.class, () -> {
            studyPostFindService.getMyPosts(pageable.getPageNumber(), pageable.getPageSize(), 1L);
        });
    }

    @Test
    void 식별자로_게시글_조회() {
        // given
        PostDataByMajor postDataByMajor = PostDataByMajor
                .builder()
                .postId(2L)
                .title("반갑습니다")
                .build();

        List<PostDataByMajor> postsByMajor = new ArrayList<>();
        postsByMajor.add(postDataByMajor);
        when(studyPostRepository.findByMajor(any(), anyLong())).thenReturn(postsByMajor);
        when(studyPostRepository.findPostById(anyLong(), anyLong())).thenReturn(Optional.ofNullable(PostData.builder().postId(1L).build()));
        when(applyRepository.findByUserIdAndStudyId(anyLong(), any())).thenReturn(Optional.empty());

        // when
        FindPostResponseById postResponse = studyPostFindService.findPostById(1L, 1L);

        assertAll(
                () -> assertThat(postResponse.getPostId()).isEqualTo(1L),
                () -> assertThat(postResponse.getRelatedPost().get(0).getTitle()).isEqualTo("반갑습니다")
        );
    }

    @Test
    void 식별자로_게시글_조회_실패_존재하지않는_게시글() {
        // given
        when(studyPostRepository.findPostById(anyLong(), anyLong())).thenReturn(Optional.empty());

        // when, then
        assertThrows(PostNotFoundException.class, () -> {
            studyPostFindService.findPostById(1L, 1L);
        });
    }
}