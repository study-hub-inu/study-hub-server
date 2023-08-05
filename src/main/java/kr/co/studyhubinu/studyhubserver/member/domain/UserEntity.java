package kr.co.studyhubinu.studyhubserver.member.domain;

import kr.co.studyhubinu.studyhubserver.common.domain.BaseTimeEntity;
import kr.co.studyhubinu.studyhubserver.member.dto.data.GeneralSignUpInfo;
import kr.co.studyhubinu.studyhubserver.member.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.member.enums.GradeType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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


    public UserEntity(GeneralSignUpInfo signUpInfo) {

        this.email = signUpInfo.getEmail();
        this.password = signUpInfo.getPassword();
        this.gender = signUpInfo.getGender();
        this.grade = signUpInfo.getGrade();

    }
}

//user 는 id, email, password, nickname, gender, grade 까지만 넣겠습니다