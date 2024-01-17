package kr.co.studyhubinu.studyhubserver.studypost.service;

import kr.co.studyhubinu.studyhubserver.exception.study.PostEndDateConflictException;
import kr.co.studyhubinu.studyhubserver.exception.study.PostNotFoundException;
import kr.co.studyhubinu.studyhubserver.exception.study.PostStartDateConflictException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotAccessRightException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.study.StudyRepository;
import kr.co.studyhubinu.studyhubserver.study.domain.StudyEntity;
import kr.co.studyhubinu.studyhubserver.studypost.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.UpdateStudyPostInfo;
import kr.co.studyhubinu.studyhubserver.studypost.dto.request.CreatePostRequest;
import kr.co.studyhubinu.studyhubserver.studypost.repository.StudyPostRepository;
import kr.co.studyhubinu.studyhubserver.support.fixture.StudyPostEntityFixture;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudyPostServiceTest {

    @InjectMocks
    StudyPostService studyPostService;

    @Mock
    StudyPostRepository studyPostRepository;

    @Mock
    StudyRepository studyRepository;

    @Mock
    UserRepository userRepository;

    @Test
    void 게시글_생성_성공() {
        // given
        Optional<UserEntity> userEntity = Optional.ofNullable(UserEntity.builder().id(1L).build());
        StudyEntity studyEntity = StudyEntity.builder().id(1L).build();
        StudyPostEntityFixture fixture = StudyPostEntityFixture.SQLD;
        CreatePostRequest postRequest = CreatePostRequest.builder().
                studyStartDate(LocalDate.of(2025, 1, 3)).
                studyEndDate(LocalDate.of(2025, 10, 5)).
                build();

        when(userRepository.findById(anyLong())).thenReturn(userEntity);
        when(studyPostRepository.save(any())).thenReturn(fixture.studyPostEntity_생성(1L, 1L));
        when(studyRepository.save(any())).thenReturn(studyEntity);

        // when
        Long postId = studyPostService.createPost(postRequest, 1L);

        // then
        assertThat(postId).isEqualTo(1L);
    }

    @Test
    void 게시글_생성_실패_시작날짜가_현재날짜_이전일경우() {
        // given
        Optional<UserEntity> userEntity = Optional.ofNullable(UserEntity.builder().id(1L).build());
        CreatePostRequest postRequest = CreatePostRequest.builder().
                studyStartDate(LocalDate.of(2023, 1, 3)).
                studyEndDate(LocalDate.of(2024, 10, 5)).
                build();

        when(userRepository.findById(anyLong())).thenReturn(userEntity);

        // when, then
        assertThrows(PostStartDateConflictException.class, () -> {
            studyPostService.createPost(postRequest, 1L);
        });
    }

    @Test
    void 게시글_생성_실패_시작날짜가_종료날짜_이후인경우() {
        // given
        Optional<UserEntity> userEntity = Optional.ofNullable(UserEntity.builder().id(1L).build());
        CreatePostRequest postRequest = CreatePostRequest.builder().
                studyStartDate(LocalDate.of(2025, 1, 3)).
                studyEndDate(LocalDate.of(2024, 10, 5)).
                build();

        when(userRepository.findById(anyLong())).thenReturn(userEntity);

        // when, then
        assertThrows(PostEndDateConflictException.class, () -> {
            studyPostService.createPost(postRequest, 1L);
        });
    }

    @Test
    void 게시글_수정_성공() {
        // given
        Optional<UserEntity> userEntity = Optional.ofNullable(UserEntity.builder().id(1L).build());
        Optional<StudyPostEntity> studyPostEntity = Optional.ofNullable(StudyPostEntityFixture.SQLD.studyPostEntity_생성(1L, 1L));
        UpdateStudyPostInfo updateStudyPostInfo = UpdateStudyPostInfo.builder().
                userId(1L).
                postId(1L).
                studyStartDate(LocalDate.of(2025, 1, 3)).
                studyEndDate(LocalDate.of(2025, 10, 5)).
                build();

        when(userRepository.findById(anyLong())).thenReturn(userEntity);
        when(studyPostRepository.findById(anyLong())).thenReturn(studyPostEntity);

        // when
        Long postId = studyPostService.updatePost(updateStudyPostInfo);

        // then
        assertThat(postId).isEqualTo(1L);
    }

    @Test
    void 게시글_수정_실패_시작날짜가_현재날짜_이전일경우() {
        // given
        Optional<UserEntity> userEntity = Optional.ofNullable(UserEntity.builder().id(1L).build());
        Optional<StudyPostEntity> studyPostEntity = Optional.ofNullable(StudyPostEntityFixture.SQLD.studyPostEntity_생성(1L));
        UpdateStudyPostInfo updateStudyPostInfo = UpdateStudyPostInfo.builder().
                userId(1L).
                postId(1L).
                studyStartDate(LocalDate.of(2023, 1, 3)).
                studyEndDate(LocalDate.of(2024, 10, 5)).
                build();

        when(userRepository.findById(anyLong())).thenReturn(userEntity);
        when(studyPostRepository.findById(anyLong())).thenReturn(studyPostEntity);

        // when, then
        assertThrows(PostStartDateConflictException.class, () -> {
            studyPostService.updatePost(updateStudyPostInfo);
        });
    }

    @Test
    void 게시글_수정_실패_시작날짜가_종료날짜_이후인경우() {
        // given
        Optional<UserEntity> userEntity = Optional.ofNullable(UserEntity.builder().id(1L).build());
        Optional<StudyPostEntity> studyPostEntity = Optional.ofNullable(StudyPostEntityFixture.SQLD.studyPostEntity_생성(1L));

        UpdateStudyPostInfo updateStudyPostInfo = UpdateStudyPostInfo.builder().
                userId(1L).
                postId(1L).
                studyStartDate(LocalDate.of(2024, 11, 3)).
                studyEndDate(LocalDate.of(2024, 10, 5)).
                build();

        when(userRepository.findById(anyLong())).thenReturn(userEntity);
        when(studyPostRepository.findById(anyLong())).thenReturn(studyPostEntity);

        // when, then
        assertThrows(PostEndDateConflictException.class, () -> {
            studyPostService.updatePost(updateStudyPostInfo);
        });
    }

    @Test
    void 게시글_삭제_성공() {
        // given
        Optional<UserEntity> userEntity = Optional.ofNullable(UserEntity.builder().id(1L).build());
        when(userRepository.findById(anyLong())).thenReturn(userEntity);
        when(studyPostRepository.findById(anyLong())).
                thenReturn(Optional.ofNullable(StudyPostEntity.builder().id(1L).postedUserId(1L).title("사랑찾아 인생찾아 울산까지 왔습니다").build()));

        doNothing().when(studyPostRepository).delete(any());

        // when, then
        studyPostService.deletePost(1L, 1L);
    }

    @Test
    void 게시글_삭제_실패_유저가_등록한_게시물이_아닐때() {
        // given
        Optional<UserEntity> userEntity = Optional.ofNullable(UserEntity.builder().id(1L).build());
        when(userRepository.findById(anyLong())).thenReturn(userEntity);
        when(studyPostRepository.findById(anyLong())).
                thenReturn(Optional.ofNullable(StudyPostEntity.builder().id(1L).postedUserId(2L).title("사랑찾아 인생찾아 울산까지 왔습니다").build()));

        // when, then
        assertThrows(UserNotAccessRightException.class, () -> {
            studyPostService.deletePost(1L, 1L);
        });
    }

    @Test
    void 게시글_게시_기한_종료_성공() {
        // given
        Optional<UserEntity> userEntity = Optional.ofNullable(UserEntity.builder().id(1L).build());
        when(userRepository.findById(anyLong())).thenReturn(userEntity);
        when(studyPostRepository.findById(anyLong())).
                thenReturn(Optional.ofNullable(StudyPostEntity.builder().id(1L).postedUserId(1L).title("사랑찾아 인생찾아 울산까지 왔습니다").build()));

        // when, then
        studyPostService.closePost(1L, 1L);
    }

    @Test
    void 게시글_게시_기한_종료_실패_존재하지않는_게시글() {
        // given
        Optional<UserEntity> userEntity = Optional.ofNullable(UserEntity.builder().id(1L).build());
        when(userRepository.findById(anyLong())).thenReturn(userEntity);
        when(studyPostRepository.findById(anyLong())).thenReturn(Optional.empty());

        // when, then
        assertThrows(PostNotFoundException.class, () -> {
            studyPostService.closePost(1L, 1L);
        });
    }

    @Test
    void 게시글_게시_기한_종료_실패_존재하지않는_유저() {
        // given
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        // when, then
        assertThrows(UserNotFoundException.class, () -> {
            studyPostService.closePost(1L, 1L);
        });
    }
}