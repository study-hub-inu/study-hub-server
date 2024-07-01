package kr.co.studyhubinu.studyhubserver.studypost.domain.implementations;

import kr.co.studyhubinu.studyhubserver.common.timer.Timer;
import kr.co.studyhubinu.studyhubserver.exception.apply.StudyApplyLockAcquisitionException;
import kr.co.studyhubinu.studyhubserver.studypost.domain.StudyPostEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class StudyPostApplyEventPublisher {

    private final RedissonClient redissonClient;
    private final StudyPostWriter studyPostWriter;
    private final StudyPostReader studyPostReader;

    public void acceptApplyEventPublish(Long studyId) {
        StudyPostEntity studyPost = studyPostReader.readByStudyId(studyId);
        RLock lock = redissonClient.getLock(studyPost.getId().toString());
        boolean available = false;
        try {
            available = lock.tryLock(10, 1, TimeUnit.SECONDS);
            if (!available) {
                throw new StudyApplyLockAcquisitionException();
            }
            studyPostWriter.updateStudyPostApply(studyPost.getId());
        } catch (InterruptedException e) {
            throw new StudyApplyLockAcquisitionException();
        } finally {
            if (available) {
                lock.unlock();
            }
        }

    }
    //    @Transactional
//    public void acceptApplyEventPublish(Long studyId) {
//        StudyPostEntity studyPost = studyPostRepository.findByIdWithPessimisticLock(studyId).orElseThrow(PostNotFoundException::new);
//        studyPost.decreaseRemainingSeat();
//        studyPost.closeStudyPostIfRemainingSeatIsZero();
//        studyPostRepository.save(studyPost);
//    }


}
