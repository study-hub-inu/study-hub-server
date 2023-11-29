package kr.co.studyhubinu.studyhubserver.studypost.repository;

import kr.co.studyhubinu.studyhubserver.studypost.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.support.fixture.StudyPostEntityFixture;
import kr.co.studyhubinu.studyhubserver.support.repository.RepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RepositoryTest
@ActiveProfiles("dev")
public class StudyPostRepositoryTest {

    @Autowired
    private StudyPostRepository studyPostRepository;

    @Test
    void 유저의_식별자로_게시글_개수를_조회한다() {
        // given
        Long userId1 = 1L;
        Long userId2 = 2L;
        StudyPostEntity post1 = StudyPostEntityFixture.SQLD.studyPostEntity_생성(3L, userId1);
        StudyPostEntity post2 = StudyPostEntityFixture.ENGINEER_INFORMATION_PROCESSING.studyPostEntity_생성(4L, userId1);
        StudyPostEntity post3 = StudyPostEntityFixture.TOEIC.studyPostEntity_생성(5L, userId1);
        studyPostRepository.save(post1);
        studyPostRepository.save(post2);
        studyPostRepository.save(post3);

        // when
        Long expectedCount = studyPostRepository.countByPostedUserId(userId1);
        Long actualCount = studyPostRepository.countByPostedUserId(userId2);

        // then
        assertEquals(expectedCount, 3);
        assertEquals(actualCount, 0);
    }


}
