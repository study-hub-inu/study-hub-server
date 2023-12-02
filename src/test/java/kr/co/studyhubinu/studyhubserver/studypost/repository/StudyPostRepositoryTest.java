package kr.co.studyhubinu.studyhubserver.studypost.repository;

import kr.co.studyhubinu.studyhubserver.studypost.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.GetMyPostData;
import kr.co.studyhubinu.studyhubserver.support.fixture.StudyPostEntityFixture;
import kr.co.studyhubinu.studyhubserver.support.repository.RepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        StudyPostEntity post1 = StudyPostEntityFixture.SQLD.studyPostEntity_생성(userId1);
        StudyPostEntity post2 = StudyPostEntityFixture.ENGINEER_INFORMATION_PROCESSING.studyPostEntity_생성(userId1);
        StudyPostEntity post3 = StudyPostEntityFixture.TOEIC.studyPostEntity_생성(userId1);
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

    @Test
    void 유저의_식별자로_게시글을_조회한다() {
        // given
        Long userId1 = 1L;
        StudyPostEntity post1 = StudyPostEntityFixture.SQLD.studyPostEntity_생성(userId1);
        StudyPostEntity post2 = StudyPostEntityFixture.ENGINEER_INFORMATION_PROCESSING.studyPostEntity_생성(userId1);
        studyPostRepository.save(post1);
        studyPostRepository.save(post2);

        // when
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createdDate"));
        Slice<GetMyPostData> expectedResult = studyPostRepository.findSliceByPostedUserId(userId1, pageable);
        List<StudyPostEntity> posts = studyPostRepository.findAll();
        System.out.println("***********************" + posts.size());

        // then
        assertThat(expectedResult.getContent()).hasSize(2);
        GetMyPostData data1 = expectedResult.getContent().get(1);
        GetMyPostData data2 = expectedResult.getContent().get(0);
        assertAll(
                () -> assertEquals(post1.getId(), data1.getPostId()),
                () -> assertEquals(post1.getContent(), data1.getContent()),
                () -> assertEquals(post2.getId(), data2.getPostId()),
                () -> assertEquals(post2.getContent(), data2.getContent())
        );
    }

    @Test
    void

}
