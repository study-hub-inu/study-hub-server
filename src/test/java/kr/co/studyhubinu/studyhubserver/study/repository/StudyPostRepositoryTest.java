package kr.co.studyhubinu.studyhubserver.study.repository;

import kr.co.studyhubinu.studyhubserver.study.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.study.enums.StudyWayType;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class StudyPostRepositoryTest {

    @Autowired
    private StudyPostRepository studyPostRepository;

    @Test
    void 게시글_저장_테스트() {
        // given
        StudyPostEntity studyPost = StudyPostEntity.builder()
                .studyPerson(10)
                .studyStartDate(LocalDate.now())
                .studyEndDate(LocalDate.now())
                .major(MajorType.COMPUTER)
                .studyWay(StudyWayType.CONTACT)
                .title("정보처리기사")
                .filteredGender(GenderType.MALE)
                .chatUrl("asdasda.com")
                .content("안녕")
                .penalty(10000)
                .build();

        // when
        StudyPostEntity studyPost1 = studyPostRepository.save(studyPost);

        //then
        Assertions.assertThat(studyPost).isEqualTo(studyPost1);
    }

}