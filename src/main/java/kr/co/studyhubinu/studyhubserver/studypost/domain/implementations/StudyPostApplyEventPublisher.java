package kr.co.studyhubinu.studyhubserver.studypost.domain.implementations;

import kr.co.studyhubinu.studyhubserver.common.redisson.RedissonDistributedLock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StudyPostApplyEventPublisher {

    private final RedissonClient redissonClient;
    private final StudyPostWriter studyPostWriter;
    private final StudyPostReader studyPostReader;

    @RedissonDistributedLock(hashKey = "'apply'", field = "#studyPostId")
    public void acceptApplyEventPublish(Long studyPostId) {
        studyPostWriter.updateStudyPostApply(studyPostId);
    }

    //        RLock lock = redissonClient.getLock(studyPostId.toString());
//        boolean available = false;
//        try {
//            available = lock.tryLock(10, 1, TimeUnit.SECONDS);
//            if (!available) {
//                throw new StudyApplyLockAcquisitionException();
//            }
//            studyPostWriter.updateStudyPostApply(studyPostId);
//        } catch (InterruptedException e) {
//            throw new StudyApplyLockAcquisitionException();
//        } finally {
//            if (available) {
//                lock.unlock();
//            }
//        }
    //    @Transactional
//    public void acceptApplyEventPublish(Long studyId) {
//        StudyPostEntity studyPost = studyPostRepository.findByIdWithPessimisticLock(studyId).orElseThrow(PostNotFoundException::new);
//        studyPost.decreaseRemainingSeat();
//        studyPost.closeStudyPostIfRemainingSeatIsZero();
//        studyPostRepository.save(studyPost);
//    }


}
