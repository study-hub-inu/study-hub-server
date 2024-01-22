package kr.co.studyhubinu.studyhubserver.emailTest;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "email")
public class EmailTestEntity {

    @Id
    @Column(name = "email_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String code;

    @Builder
    public EmailTestEntity(Long id, String email, String code) {
        this.id = id;
        this.email = email;
        this.code = code;
    }
}
