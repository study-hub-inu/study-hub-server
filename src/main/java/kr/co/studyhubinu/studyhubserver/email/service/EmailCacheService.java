package kr.co.studyhubinu.studyhubserver.email.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class EmailCacheService {

    private final StringRedisTemplate redisTemplate;

    public String getAndCacheAuthCode(String email) {
        Random random = new Random();
        StringBuilder key = new StringBuilder();

        for(int i=0;i<8;i++) {
            key.append(random.nextInt(10));
        }
        redisTemplate.opsForValue().set(email, key.toString(), 1000L * 60 * 5, TimeUnit.MILLISECONDS);
        return key.toString();
    }

}