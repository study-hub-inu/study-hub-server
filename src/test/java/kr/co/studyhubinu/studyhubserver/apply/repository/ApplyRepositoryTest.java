package kr.co.studyhubinu.studyhubserver.apply.repository;

import kr.co.studyhubinu.studyhubserver.apply.domain.ApplyEntity;
import kr.co.studyhubinu.studyhubserver.apply.dto.data.ApplyUserData;
import kr.co.studyhubinu.studyhubserver.apply.dto.request.UpdateApplyRequest;
import kr.co.studyhubinu.studyhubserver.apply.enums.Inspection;
import kr.co.studyhubinu.studyhubserver.exception.apply.ApplyNotFoundException;
import kr.co.studyhubinu.studyhubserver.study.StudyRepository;
import kr.co.studyhubinu.studyhubserver.study.domain.StudyEntity;
import kr.co.studyhubinu.studyhubserver.support.fixture.StudyEntityFixture;
import kr.co.studyhubinu.studyhubserver.support.fixture.UserEntityFixture;
import kr.co.studyhubinu.studyhubserver.support.repository.RepositoryTest;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

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
                .study(study.getId())
                .inspection(Inspection.ACCEPT)
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
                .study(study.getId())
                .inspection(Inspection.ACCEPT)
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
                .study(study.getId())
                .inspection(Inspection.ACCEPT)
                .build();
        applyRepository.save(apply);
        applyRepository.flush();

        userRepository.delete(user);
        userRepository.flush();

        // when
        ApplyEntity result = applyRepository.findByUserIdAndStudyId(user.getId(), study.getId()).orElseThrow(ApplyNotFoundException::new);

        // then
        assertAll(
                () -> assertEquals(result.getUserId(), user.getId()),
                () -> assertEquals(result.getStudyId(), apply.getId())
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
                .study(study.getId())
                .inspection(Inspection.ACCEPT)
                .introduce("벌금내러 왔습니다.")
                .build();

        ApplyEntity apply2 = ApplyEntity.builder()
                .userId(user2.getId())
                .study(study.getId())
                .inspection(Inspection.ACCEPT)
                .introduce("목숨을 걸겠습니다.")
                .build();

        ApplyEntity result1 = applyRepository.save(apply1);
        ApplyEntity result2 = applyRepository.save(apply2);
        List<ApplyEntity> list = applyRepository.findAll();

        // when
        List<ApplyUserData> result = applyRepository.findByStudy(study.getId(), pageable);

        // then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void 스터디_참가요청_조회_유저데이터_반환() {

    }
}