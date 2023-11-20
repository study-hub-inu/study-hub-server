package kr.co.studyhubinu.studyhubserver.user.service;

import kr.co.studyhubinu.studyhubserver.study.enums.StudyWayType;
import kr.co.studyhubinu.studyhubserver.studypost.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.studypost.domain.StudyPostValidator;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.StudyPostInfo;
import kr.co.studyhubinu.studyhubserver.studypost.repository.StudyPostRepository;
import kr.co.studyhubinu.studyhubserver.studypost.service.StudyPostService;
import kr.co.studyhubinu.studyhubserver.support.fixture.UserEntityFixture;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StudyPostServiceTest {
    @InjectMocks
    private StudyPostService studyPostService;
    @Mock
    private StudyPostRepository studyPostRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private StudyPostValidator studyPostValidator;

    @Test
    @DisplayName("스터디 포스트 생성 성공")
    void createPost_success() {
        // given
        Long userId = 1L;
        StudyPostInfo info = StudyPostInfo.builder()
                .userId(userId)
                .title("정보처리기사")
                .content("열심히 하실분만")
                .chatUrl("example.com")
                .major(MajorType.COMPUTER_SCIENCE_ENGINEERING)
                .studyPerson(4)
                .penalty(100000)
                .penaltyWay("지각비")
                .gender(GenderType.MALE)
                .studyWay(StudyWayType.MIX)
                .studyStartDate(LocalDate.of(2023, 10, 25))
                .studyEndDate(LocalDate.of(2023, 12, 25))
                .build();
        UserEntity user1 = UserEntityFixture.DONGWOO.UserEntity_생성(userId);

        BDDMockito.given(userRepository.findById(info.getUserId())).willReturn(Optional.of(user1));
        BDDMockito.given(studyPostRepository.save(Mockito.any(StudyPostEntity.class)))
                .willAnswer(invocation -> {
                    StudyPostEntity savedPost = invocation.getArgument(0);

                    // 새로운 빌더 객체를 만들어서 기존 속성들을 복사하고 ID를 설정
                    StudyPostEntity fakeSavedPost = StudyPostEntity.builder()
                            .id(1L) // 가짜 ID 설정
                            .build();
                    return fakeSavedPost;
                });
        ArgumentCaptor<StudyPostEntity> captor = ArgumentCaptor.forClass(StudyPostEntity.class);

        // when
        Long postId = studyPostService.createPost(info);

        // then
        verify(studyPostRepository, times(1))
                .save(captor.capture());
        StudyPostEntity capturedStudyPost = captor.getValue();
        assertEquals("정보처리기사", capturedStudyPost.getTitle());

        assertNotNull(postId);

    }
}
