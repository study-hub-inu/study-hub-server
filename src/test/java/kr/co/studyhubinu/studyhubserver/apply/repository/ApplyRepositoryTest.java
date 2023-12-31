package kr.co.studyhubinu.studyhubserver.apply.repository;

import kr.co.studyhubinu.studyhubserver.apply.domain.ApplyEntity;
import kr.co.studyhubinu.studyhubserver.apply.dto.request.UpdateApplyRequest;
import kr.co.studyhubinu.studyhubserver.apply.enums.Inspection;
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

import javax.persistence.EntityManager;

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

    @Test
    void 스터디_가입_신청() {
        // given
        UserEntity user = userRepository.save(UserEntityFixture.DONGWOO.UserEntity_생성());
        StudyEntity study = studyRepository.save(StudyEntityFixture.INU.studyEntity_생성());

        // when
        ApplyEntity apply = ApplyEntity.builder()
                .user(user)
                .study(study)
                .inspection(Inspection.ACCEPT)
                .build();
        ApplyEntity result = applyRepository.save(apply);

        // then
        assertAll(
                () -> assertEquals(result.getUser().getId(), user.getId()),
                () -> assertEquals(result.getStudy().getId(), study.getId())
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
                .user(user)
                .study(study)
                .inspection(Inspection.ACCEPT)
                .build();
        applyRepository.save(applyEntity);
        applyRepository.flush();

        ApplyEntity apply = applyRepository.findById(1L).orElseThrow(IllegalArgumentException::new);
        apply.update(updateApplyRequest.getInspection());
        applyRepository.flush();
        ApplyEntity result = applyRepository.findById(1L).orElseThrow(IllegalArgumentException::new);

        // then
        assertThat(result.getInspection()).isEqualTo(Inspection.STANDBY);
    }

    @Test
    void 유저식별자와_스터디식별자로_스터디요청_엔티티_조회() {
        // given
        UserEntity user = userRepository.save(UserEntityFixture.DONGWOO.UserEntity_생성());
        StudyEntity study = studyRepository.save(StudyEntityFixture.INU.studyEntity_생성());
        ApplyEntity apply = ApplyEntity.builder()
                .user(user)
                .study(study)
                .inspection(Inspection.ACCEPT)
                .build();
        applyRepository.save(apply);
        applyRepository.flush();

        // when
        ApplyEntity result = applyRepository.findByUserAndStudy(user, study);

        // then
        assertAll(
                () -> assertEquals(result.getUser().getId(), user.getId()),
                () -> assertEquals(result.getStudy().getId(), study.getId())
        );
    }

}