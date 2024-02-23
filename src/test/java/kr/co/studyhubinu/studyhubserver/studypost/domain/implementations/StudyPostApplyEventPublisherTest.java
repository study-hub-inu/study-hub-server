package kr.co.studyhubinu.studyhubserver.studypost.domain.implementations;

import kr.co.studyhubinu.studyhubserver.studypost.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.studypost.repository.StudyPostRepository;
import kr.co.studyhubinu.studyhubserver.support.fixture.StudyPostEntityFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StudyPostApplyEventPublisherTest {

    @Autowired
    private StudyPostApplyEventPublisher studyPostApplyEventPublisher;
    @Autowired
    private StudyPostRepository studyPostRepository;

    @Test
    void 스터디_지원서가_수락되면_게시글의_잔여석이_줄어든다() throws InterruptedException {
        // given
        Long postedUserId = 1L;
        StudyPostEntity post = StudyPostEntityFixture.SQLD.studyPostEntity_생성(postedUserId);
        StudyPostEntity savedPost = studyPostRepository.saveAndFlush(post);
        // when
        int threadCount = 100;
        ExecutorService executorService = newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    studyPostApplyEventPublisher.acceptApplyEventPublish(post.getStudyId());
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        // then
        StudyPostEntity actualStudyPost = studyPostRepository.findById(savedPost.getId()).orElseThrow();
        // 100 - (1 * 100) = 0이 되어야 함
        assertEquals(0, actualStudyPost.getRemainingSeat());

    }

}