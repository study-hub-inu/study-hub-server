package kr.co.studyhubinu.studyhubserver.studypost.service;

import kr.co.studyhubinu.studyhubserver.exception.study.PostNotFoundException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotAccessRightException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.studypost.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.studypost.repository.StudyPostRepository;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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
    UserRepository userRepository;

    @Test
    void 게시글_생성_성공() {
        // given

        // when

        // then

    }

    @Test
    void updatePost() {
    }

    @Test
    void 게시글_삭제_성공() {
        // given
        Optional<UserEntity> userEntity = Optional.ofNullable(UserEntity.builder().id(1L).build());
        when(userRepository.findById(anyLong())).thenReturn(userEntity);
        when(studyPostRepository.findById(anyLong())).
                thenReturn(Optional.ofNullable(StudyPostEntity.builder().id(1L).userId(1L).title("사랑찾아 인생찾아 울산까지 왔습니다").build()));

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
                thenReturn(Optional.ofNullable(StudyPostEntity.builder().id(1L).userId(2L).title("사랑찾아 인생찾아 울산까지 왔습니다").build()));

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
                thenReturn(Optional.ofNullable(StudyPostEntity.builder().id(1L).userId(1L).title("사랑찾아 인생찾아 울산까지 왔습니다").build()));

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