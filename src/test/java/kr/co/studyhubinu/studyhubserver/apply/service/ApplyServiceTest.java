package kr.co.studyhubinu.studyhubserver.apply.service;


import kr.co.studyhubinu.studyhubserver.apply.domain.ApplyEntity;
import kr.co.studyhubinu.studyhubserver.apply.dto.data.ApplyUserData;
import kr.co.studyhubinu.studyhubserver.apply.dto.request.EnrollApplyRequest;
import kr.co.studyhubinu.studyhubserver.apply.dto.request.FindApplyRequest;
import kr.co.studyhubinu.studyhubserver.apply.dto.response.FindApplyResponse;
import kr.co.studyhubinu.studyhubserver.apply.enums.Inspection;
import kr.co.studyhubinu.studyhubserver.apply.repository.ApplyRepository;
import kr.co.studyhubinu.studyhubserver.study.domain.StudyEntity;
import kr.co.studyhubinu.studyhubserver.study.repository.StudyRepository;
import kr.co.studyhubinu.studyhubserver.studypost.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.studypost.repository.StudyPostRepository;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserId;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplyServiceTest {

    @InjectMocks
    ApplyService applyService;

    @Mock
    UserRepository userRepository;

    @Mock
    StudyRepository studyRepository;

    @Mock
    ApplyRepository applyRepository;

    @Mock
    StudyPostRepository studyPostRepository;

    @Test
    void 스터디_가입신청() {
        // given
        EnrollApplyRequest request = EnrollApplyRequest.builder()
                .studyId(1L)
                .build();
        UserEntity user = UserEntity.builder().id(1L).build();
        StudyEntity study = StudyEntity.builder().id(1L).build();
        StudyPostEntity studyPost = StudyPostEntity.builder().id(1L).build();

        when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(user));
        when(studyRepository.findById(anyLong())).thenReturn(Optional.ofNullable(study));
        when(applyRepository.save(any())).thenReturn(ApplyEntity.builder()
                .userId(user.getId())
                .studyId(study.getId())
                .build());
        when(studyPostRepository.findByStudyId(anyLong())).thenReturn(Optional.ofNullable(studyPost));

        // when, then
        applyService.enroll(new UserId(1L), request);
    }

    /**
     * 비즈니스 레이어 단위테스트 의미있는 작업인지 고민중
     */

//    @Test
//    void 스터디_신청상태_변경() {
//        // given
//        UpdateApplyRequest request = UpdateApplyRequest.builder()
//                .userId(1L)
//                .studyId(1L)
//                .inspection(Inspection.ACCEPT)
//                .build();
//
//        Optional<UserEntity> user = Optional.ofNullable(UserEntity.builder().id(1L).build());
//        Optional<StudyEntity> study = Optional.ofNullable(StudyEntity.builder().id(1L).build());
//        when(userRepository.findById(anyLong())).thenReturn(user);
//        when(studyRepository.findById(anyLong())).thenReturn(study);
//        when(applyRepository.findByUserIdAndStudyId(1L, 1L)).thenReturn(Optional.ofNullable(ApplyEntity.builder().build()));
//        when(studyPostRepository.findByStudyId()).thenReturn()
//
//        // when, then
//        applyService.update(request);
//    }

    @Test
    void 스터디_요청상태_조회() {
        // given
        FindApplyRequest request = FindApplyRequest.builder()
                .studyId(1L)
                .inspection(Inspection.ACCEPT)
                .build();
        ApplyUserData data1 = ApplyUserData.builder()
                .nickname("liljay")
                .inspection(Inspection.ACCEPT)
                .build();
        ApplyUserData data2 = ApplyUserData.builder().build();

        List<ApplyUserData> applyEntities = List.of(data1, data2);

        when(applyRepository.findStudyByIdAndInspection(request, PageRequest.of(0,5))).thenReturn(applyEntities);

        // when
        FindApplyResponse applyResponse = applyService.findApply(request, 0, 5);

        // then
        assertAll(
                () -> assertEquals(applyResponse.getTotalCount(), 5L),
                () -> assertEquals(applyResponse.getApplyUserData().getContent().get(0).getNickname(), "liljay")
        );
    }
}