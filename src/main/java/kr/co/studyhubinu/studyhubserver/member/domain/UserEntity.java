package kr.co.studyhubinu.studyhubserver.member.domain;

import kr.co.studyhubinu.studyhubserver.common.domain.BaseTimeEntity;
import kr.co.studyhubinu.studyhubserver.member.dto.data.GeneralSignUpInfo;
import kr.co.studyhubinu.studyhubserver.member.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.member.enums.GradeType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String password;

    private GenderType gender;

    private GradeType grade;


    @Builder
    public UserEntity(String email, String password, GenderType gender, GradeType grade) {
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.grade = grade;
    }
}

//user 는 id, email, password, nickname, gender, grade 까지만 넣겠습니다