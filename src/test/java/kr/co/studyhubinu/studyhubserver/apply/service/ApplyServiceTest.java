package kr.co.studyhubinu.studyhubserver.apply.service;


import kr.co.studyhubinu.studyhubserver.apply.domain.ApplyEntity;
import kr.co.studyhubinu.studyhubserver.apply.dto.request.EnrollApplyRequest;
import kr.co.studyhubinu.studyhubserver.apply.dto.request.FindApplyRequest;
import kr.co.studyhubinu.studyhubserver.apply.dto.request.UpdateApplyRequest;
import kr.co.studyhubinu.studyhubserver.apply.enums.Inspection;
import kr.co.studyhubinu.studyhubserver.apply.repository.ApplyRepository;
import kr.co.studyhubinu.studyhubserver.study.StudyRepository;
import kr.co.studyhubinu.studyhubserver.study.domain.StudyEntity;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Test
    void 스터디_가입신청() {
        // given
        EnrollApplyRequest request = EnrollApplyRequest.builder()
                .studyId(1L)
                .build();
        Optional<UserEntity> user = Optional.ofNullable(UserEntity.builder().id(1L).build());
        Optional<StudyEntity> study = Optional.ofNullable(StudyEntity.builder().id(1L).build());

        when(userRepository.findById(anyLong())).thenReturn(user);
        when(studyRepository.findById(anyLong())).thenReturn(study);
        when(applyRepository.save(any())).thenReturn(ApplyEntity.builder()
                .user(user.get().getId())
                .study(study.get().getId())
                .build());

        // when, then
        applyService.enroll(user.get().getId(), request);
    }

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
//        when(applyRepository.findByUserAndStudy(any(), any())).thenReturn(ApplyEntity.builder().build());
//
//        // when, then
//        applyService.update(request);
//    }

//    @Test
//    void 스터디_요청상태_조회() {
//        // given
//        FindApplyRequest request = FindApplyRequest.builder()
//                .studyId(1L)
//                .build();
//        ApplyEntity apply1 = ApplyEntity.builder().build();
//        ApplyEntity apply2 = ApplyEntity.builder().build();
//
//        List<ApplyEntity> applyEntities = List.of(apply1, apply2);
//
//        when(applyRepository.findByStudy(any())).thenReturn(applyEntities);
//
//        // when
//        applyService.findApply(request);
//
//        // then
//    }
}