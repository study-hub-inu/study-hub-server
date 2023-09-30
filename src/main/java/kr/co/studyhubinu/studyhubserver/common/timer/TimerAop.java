package kr.co.studyhubinu.studyhubserver.common.timer;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class TimerAop {
    @Pointcut("@annotation(kr.co.studyhubinu.studyhubserver.common.timer.Timer)")
    private void enableTimer() {}

    @Around("enableTimer()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();
        log.info("********************" + String.valueOf(stopWatch.getTotalTimeMillis()) + "millis");
        return result;
    }
}