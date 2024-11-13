package kr.co.studyhubinu.studyhubserver.common.redisson;

import kr.co.studyhubinu.studyhubserver.exception.apply.LockAcquisitionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Aspect
@Slf4j
@RequiredArgsConstructor
@Component
public class RedissonDistributedLockAop {

    private final RedissonClient redissonClient;
    private static final int LOCK_WAIT_TIME = 3;
    private static final int LOCK_LEASE_TIME = 1;

    @Around("@annotation(kr.co.studyhubinu.studyhubserver.common.redisson.RedissonDistributedLock)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RedissonDistributedLock redissonDistributedLock = method.getAnnotation(RedissonDistributedLock.class);
        String hashKey = getDynamicValue(signature, joinPoint, redissonDistributedLock.hashKey());
        String field = getDynamicValue(signature, joinPoint, redissonDistributedLock.field());

        RLock lock = redissonClient.getLock(hashKey + ":" + field);

        Object result;
        boolean available = false;
        try {
            available = lock.tryLock(LOCK_WAIT_TIME, LOCK_LEASE_TIME, TimeUnit.SECONDS);
            if (!available) {
                log.warn("Redisson GetLock Timeout {}", field);
                throw new LockAcquisitionException();
            }

            result = joinPoint.proceed();
        } catch (InterruptedException e) {
            throw new LockAcquisitionException();
        } finally {
            if (available) {
                lock.unlock();
            }
        }
        return result;
    }

    // 메서드 파라미터(field와 hashkey)를 기반으로 동적으로 값을 지정
    public String getDynamicValue(MethodSignature signature, ProceedingJoinPoint joinPoint, String distributedLock) {
        return CustomSpringELParser.getDynamicValue(
                signature.getParameterNames(),
                joinPoint.getArgs(),
                distributedLock);
    }
}