package kr.co.studyhubinu.studyhubserver.email.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailCacheService {

    @Cacheable(value = "authNumCache", key = "#email")
    public String getAndCacheAuthCode(String email) {
        Random random = new Random();
        StringBuilder key = new StringBuilder();

        for(int i=0;i<8;i++) {
            key.append(random.nextInt(10));
        }
        return key.toString();
    }

}
