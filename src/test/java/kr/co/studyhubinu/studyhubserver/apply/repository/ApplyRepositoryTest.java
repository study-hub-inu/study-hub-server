package kr.co.studyhubinu.studyhubserver.apply.repository;

import kr.co.studyhubinu.studyhubserver.apply.domain.ApplyEntity;
import kr.co.studyhubinu.studyhubserver.apply.enums.Inspection;
import kr.co.studyhubinu.studyhubserver.study.StudyRepository;
import kr.co.studyhubinu.studyhubserver.study.domain.StudyEntity;
import kr.co.studyhubinu.studyhubserver.support.fixture.StudyEntityFixture;
import kr.co.studyhubinu.studyhubserver.support.fixture.UserEntityFixture;
import kr.co.studyhubinu.studyhubserver.support.repository.RepositoryTest;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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

}