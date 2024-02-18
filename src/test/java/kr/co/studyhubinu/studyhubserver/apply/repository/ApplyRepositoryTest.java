package kr.co.studyhubinu.studyhubserver.apply.repository;

import kr.co.studyhubinu.studyhubserver.apply.domain.ApplyEntity;
import kr.co.studyhubinu.studyhubserver.apply.dto.data.ApplyUserData;
import kr.co.studyhubinu.studyhubserver.apply.dto.data.RequestApplyData;
import kr.co.studyhubinu.studyhubserver.apply.dto.request.UpdateApplyRequest;
import kr.co.studyhubinu.studyhubserver.apply.enums.Inspection;
import kr.co.studyhubinu.studyhubserver.exception.apply.ApplyNotFoundException;
import kr.co.studyhubinu.studyhubserver.study.repository.StudyRepository;
import kr.co.studyhubinu.studyhubserver.study.domain.StudyEntity;
import kr.co.studyhubinu.studyhubserver.studypost.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.studypost.repository.StudyPostRepository;
import kr.co.studyhubinu.studyhubserver.support.fixture.StudyEntityFixture;
import kr.co.studyhubinu.studyhubserver.support.fixture.UserEntityFixture;
import kr.co.studyhubinu.studyhubserver.support.repository.RepositoryTest;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static kr.co.studyhubinu.studyhubserver.apply.enums.Inspection.ACCEPT;
import static kr.co.studyhubinu.studyhubserver.apply.enums.Inspection.REJECT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


@RepositoryTest
class ApplyRepositoryTest {

    @Autowired
    ApplyRepository applyRepository;

    @Autowired
    StudyRepository studyRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StudyPostRepository studyPostRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void 스터디_가입_신청() {
        // given
        UserEntity user = userRepository.save(UserEntityFixture.DONGWOO.UserEntity_생성());
        StudyEntity study = studyRepository.save(StudyEntityFixture.INU.studyEntity_생성());

        // when
        ApplyEntity apply = ApplyEntity.builder()
                .userId(user.getId())
                .studyId(study.getId())
                .inspection(ACCEPT)
                .build();
        ApplyEntity result = applyRepository.save(apply);

        // then
        assertAll(
                () -> assertEquals(result.getUserId(), user.getId()),
                () -> assertEquals(result.getStudyId(), study.getId())
        );
    }

    @Test
    void 스터디_신청_상태_변경() {
        // given
        UserEntity user = userRepository.save(UserEntityFixture.DONGWOO.UserEntity_생성());
        StudyEntity study = studyRepository.save(StudyEntityFixture.INU.studyEntity_생성());
        UpdateApplyRequest updateApplyRequest = UpdateApplyRequest.builder()
                .userId(user.getId())
                .studyId(study.getId())
                .inspection(Inspection.STANDBY)
                .build();

        // when
        ApplyEntity applyEntity = ApplyEntity.builder()
                .userId(user.getId())
                .studyId(study.getId())
                .inspection(ACCEPT)
                .build();
        applyRepository.save(applyEntity);
        applyRepository.flush();

        Optional<ApplyEntity> apply = applyRepository.findByUserIdAndStudyId(user.getId(), study.getId());
        apply.get().update(updateApplyRequest.getInspection());
        applyRepository.flush();
        Optional<ApplyEntity> result = applyRepository.findByUserIdAndStudyId(user.getId(), study.getId());

        // then
        assertThat(result.get().getInspection()).isEqualTo(Inspection.STANDBY);
    }

    @Test
    void 유저식별자와_스터디식별자로_스터디요청_엔티티_조회() {
        // given
        UserEntity user = userRepository.save(UserEntityFixture.DONGWOO.UserEntity_생성());
        StudyEntity study = studyRepository.save(StudyEntityFixture.INU.studyEntity_생성());
        ApplyEntity apply = ApplyEntity.builder()
                .userId(user.getId())
                .studyId(study.getId())
                .inspection(ACCEPT)
                .build();
        applyRepository.save(apply);
        applyRepository.flush();

        userRepository.delete(user);
        userRepository.flush();

        // when
        ApplyEntity result = applyRepository.findByUserIdAndStudyId(user.getId(), study.getId()).orElseThrow(ApplyNotFoundException::new);

        // then
        assertAll(
                () -> assertEquals(result.getUserId(), apply.getUserId()),
                () -> assertEquals(result.getStudyId(), apply.getStudyId())
        );
    }

    @Test
    void 스터디로_스터디_요청_조회() {
        // given
        UserEntity user = userRepository.save(UserEntityFixture.DONGWOO.UserEntity_생성());
        UserEntity user2 = userRepository.save(UserEntityFixture.JOOWON.UserEntity_생성());

        StudyEntity study = studyRepository.save(StudyEntityFixture.INU.studyEntity_생성());

        Pageable pageable = PageRequest.of(0,2);

        ApplyEntity apply1 = ApplyEntity.builder()
                .userId(user.getId())
                .studyId(study.getId())
                .inspection(ACCEPT)
                .introduce("벌금내러 왔습니다.")
                .build();

        ApplyEntity apply2 = ApplyEntity.builder()
                .userId(user2.getId())
                .studyId(study.getId())
                .inspection(ACCEPT)
                .introduce("목숨을 걸겠습니다.")
                .build();

        applyRepository.save(apply1);
        applyRepository.save(apply2);

        // when
        List<ApplyUserData> result = applyRepository.findByStudy(study.getId(), pageable);

        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getInspection()).isEqualTo(ACCEPT);
    }

    @Test
    void 로그인하지_않은_유저가_조회할_경우_반환값_없음() {
        // given
        applyRepository.save(ApplyEntity.builder().userId(1L).studyId(1L).build());

        // when
        Optional<ApplyEntity> apply = applyRepository.findByUserIdAndStudyId(null, 1L);

        // then
        assertThat(apply).isEmpty();
    }

    @Test
    void 내가_신청한_스터디_조회_시_Apply된_스터디를_제외하고_조회() {
        UserEntity userEntity = userRepository.save(UserEntityFixture.DONGWOO.UserEntity_생성());
        StudyEntity studyEntity = studyRepository.save(StudyEntityFixture.INU.studyEntity_생성());
        StudyPostEntity studyPostEntity = StudyPostEntity.builder()
                .studyId(studyEntity.getId())
                .postedUserId(userEntity.getId())
                .build();
        studyPostRepository.save(studyPostEntity);

        ApplyEntity apply = ApplyEntity.builder()
                .userId(userEntity.getId())
                .studyId(studyEntity.getId())
                .inspection(ACCEPT)
                .build();
        applyRepository.save(apply);

        List<RequestApplyData> applyData = applyRepository.findApplyByUserId(userEntity.getId(), PageRequest.of(0,3));
        assertThat(applyData.size()).isEqualTo(0);
    }
}