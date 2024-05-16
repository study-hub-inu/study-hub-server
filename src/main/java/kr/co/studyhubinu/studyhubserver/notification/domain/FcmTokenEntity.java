package kr.co.studyhubinu.studyhubserver.notification.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//@Entity
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Table(name = "fcm_token")
//public class FcmTokenEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "fcm_token_id")
//    private Long id;
//
//    @Column(name = "user_id")
//    private Long userId;
//
//    private String token;
//
//    public FcmTokenEntity(Long userId, String token) {
//        this.userId = userId;
//        this.token = token;
//    }
//
//    @Builder
//
//
//    public void updateToken(String token) {
//        this.token = token;
//    }
//}
